package com.github.randombear.allstatdota.activities;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.VolleyError;
import com.github.randombear.allstatdota.R;
import com.github.randombear.allstatdota.adapter.MatchDetailsAdapter;
import com.github.randombear.allstatdota.dataaccessobject.entities.Match;
import com.github.randombear.allstatdota.dataaccessobject.entities.MatchDetails;
import com.github.randombear.allstatdota.dataaccessobject.entities.MatchHistory;
import com.github.randombear.allstatdota.dataaccessobject.interfaces.VolleyCallback;
import com.github.randombear.allstatdota.dataaccessobject.local.StatDbHelper;
import com.github.randombear.allstatdota.dataaccessobject.remote.DotaDataRequest;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MatchListFragment extends Fragment {

    private static final String TAG = "Main Activity";

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private int mDetailsCounter;
    private ArrayList<MatchDetails> mDetailedMatchList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View rootView = inflater.inflate(R.layout.fragment_matchlist, container, false);
        mRecyclerView = rootView.findViewById(R.id.content_main_recyclerView);
        mSwipeRefreshLayout = rootView.findViewById(R.id.content_main_swiperefresh);
        //Using a Linear Layout Manager
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        if (doesDatabaseExist(container.getContext(),"dotaStat.db")) {
            Log.i(TAG,"Database exists at" + container.getContext()
                    .getDatabasePath("dotaStat.db"));
            AsyncTask<Void,Void,List<MatchDetails>> dbReadTask = new DatabaseReadMatchDetailsTask();
            dbReadTask.execute();
        } else {
            Log.i(TAG,"Database doesn't exists");
            DotaDataRequest dataRequest = new DotaDataRequest(getContext());
            dataRequest.getMatchHistory(new VolleyCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {
                    refreshAndUpdate(result);
                }

                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                }
            });
        }

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                DotaDataRequest dataRequest = new DotaDataRequest(getContext());
                getContext().deleteDatabase("dotaStat.db");
                dataRequest.getMatchHistory(new VolleyCallback() {
                    @Override
                    public void onSuccessResponse(JSONObject result) {
                        refreshAndUpdate(result);
                    }

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });
            }
        });

        return rootView;
    }

    /**
     * Helper function that creates a new MatchHistory object from the JSON, populates the
     * database and prepares a new adapter to be filled with the new data.
     * @param result
     */
    private void refreshAndUpdate(JSONObject result) {
        MatchHistory matchHistory = MatchHistory.createFromJSON(result);
        mDetailsCounter = matchHistory.getMatches().size();
        mDetailedMatchList = new ArrayList<>();
        for (Match m : matchHistory.getMatches()) {
            DotaDataRequest request = new DotaDataRequest(getContext());
            request.getMatchDetails("" + m.getMatchId(), new VolleyCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {
                    MatchDetails matchDetails = MatchDetails.createFromJSON(result);
                    mDetailedMatchList.add(matchDetails);
                    mDetailsCounter--;
                    if (mDetailsCounter == 0) {
                        DatabasePutMatchDetailsTask matchDetailsTask =
                                new DatabasePutMatchDetailsTask();
                        matchDetailsTask.execute(mDetailedMatchList);
                        //noinspection Since15
                        mDetailedMatchList.sort(new Comparator<MatchDetails>() {
                            @Override
                            public int compare(MatchDetails matchDetails, MatchDetails t1) {
                                return matchDetails.getMatchSeqNum() <= t1.getMatchSeqNum() ? 1 : -1;
                            }
                        });
                        mAdapter = new MatchDetailsAdapter(mDetailedMatchList,getContext());
                        mRecyclerView.setAdapter(mAdapter);
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                }

                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                    mDetailsCounter--;
                }
            });
        }
    }

    /**
     * Functions that tells whether the database file exists or not.
     * @param context           Application context
     * @param dbName            Database name.
     * @return                  True if the file exists, False otherwise.
     */
    private static boolean doesDatabaseExist(Context context, String dbName) {
        File dbFile = context.getDatabasePath(dbName);
        return dbFile.exists();
    }

    private class DatabasePutMatchDetailsTask extends AsyncTask<ArrayList<MatchDetails>, Void, ArrayList<MatchDetails>> {
        @Override
        protected ArrayList<MatchDetails> doInBackground(ArrayList<MatchDetails> ... matchDetails) {
            StatDbHelper dbHelper = new StatDbHelper(getContext());
            dbHelper.putMatchDetails(matchDetails[0]);
            return matchDetails[0];
        }
    }

    private class DatabaseReadMatchDetailsTask extends AsyncTask<Void,Void,List<MatchDetails>> {
        @Override
        protected List<MatchDetails> doInBackground(Void... voids) {
            StatDbHelper dbHelper = new StatDbHelper(getContext());
            return dbHelper.readMatchDetailsFromDatabase();
        }

        @Override
        protected void onPostExecute(List<MatchDetails> matchDetails) {
            Log.d(TAG, "Total number of MatchDetails entries read: " + matchDetails.size());
            mDetailedMatchList = (ArrayList<MatchDetails>) matchDetails;
            EventBus.getDefault().post(new MessageEvent(mDetailedMatchList.toArray(new MatchDetails[mDetailedMatchList.size()])));
            mAdapter = new MatchDetailsAdapter(mDetailedMatchList,getContext());
            mRecyclerView.setAdapter(mAdapter);
        }
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static MatchListFragment newInstance(int sectionNumber) {
        MatchListFragment fragment = new MatchListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }
}
