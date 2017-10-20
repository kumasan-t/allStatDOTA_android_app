package com.github.randombear.allstatdota.activities;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.VolleyError;
import com.github.randombear.allstatdota.R;
import com.github.randombear.allstatdota.adapter.MatchAdapter;
import com.github.randombear.allstatdota.dataaccessobject.entities.Match;
import com.github.randombear.allstatdota.dataaccessobject.entities.MatchDetails;
import com.github.randombear.allstatdota.dataaccessobject.entities.MatchHistory;
import com.github.randombear.allstatdota.dataaccessobject.interfaces.VolleyCallback;
import com.github.randombear.allstatdota.dataaccessobject.local.StatDbHelper;
import com.github.randombear.allstatdota.dataaccessobject.remote.DotaDataRequest;

import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Main Activity";

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private int mDetailsCounter;
    private ArrayList<MatchDetails> mDetailedMatchList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.content_main_recyclerView);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.content_main_swiperefresh);
        //Using a Linear Layout Manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        if (doesDatabaseExist(getBaseContext(),"dotaStat.db")) {
            Log.i(TAG,"Database exists at" + getBaseContext()
                    .getDatabasePath("dotaStat.db"));
            AsyncTask<Void,Void,List<MatchDetails>> dbReadTask = new DatabaseReadMatchDetailsTask();
            dbReadTask.execute();
        } else {
            Log.i(TAG,"Database doesn't exists");
            DotaDataRequest dataRequest = new DotaDataRequest(getBaseContext());
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
                DotaDataRequest dataRequest = new DotaDataRequest(getBaseContext());
                getBaseContext().deleteDatabase("dotaStat.db");
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
    }

    /**
     * Helper function that creates a new MatchHistory object from the JSON, populates the
     * database and prepares a new adapter to be filled with the new data.
     * @param result
     */
    private void refreshAndUpdate(JSONObject result) {
        MatchHistory matchHistory = MatchHistory.createFromJSON(result);
        DatabasePopulateTask dbPopulate = new DatabasePopulateTask();
        dbPopulate.execute(matchHistory);
        mAdapter = new MatchAdapter(matchHistory,getBaseContext());
        mRecyclerView.setAdapter(mAdapter);
        mSwipeRefreshLayout.setRefreshing(false);
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

    private class DatabaseReadTask extends AsyncTask<Void,Void,MatchHistory> {
        @Override
        protected MatchHistory doInBackground(Void ... voids) {
            StatDbHelper dbHelper = new StatDbHelper(getBaseContext());
            return dbHelper.readMatchHistoryFromDatabase();
        }

        protected void onPostExecute(MatchHistory matchHistory) {
            mAdapter = new MatchAdapter(matchHistory,getBaseContext());
            mRecyclerView.setAdapter(mAdapter);
        }
    }

    private class DatabasePopulateTask extends AsyncTask<MatchHistory,Void,MatchHistory[]> {
        @Override
        protected MatchHistory[] doInBackground(MatchHistory... matchHistories) {
            StatDbHelper dbHelper = new StatDbHelper(getBaseContext());
            for (MatchHistory m : matchHistories) {
                dbHelper.populateDatabase(m);
            }
            return matchHistories;
        }

        @Override
        protected void onPostExecute(MatchHistory[] matchHistory) {
            mDetailsCounter = matchHistory[0].getMatches().size();
            mDetailedMatchList = new ArrayList<>();
            for (Match m : matchHistory[0].getMatches()) {
                DotaDataRequest request = new DotaDataRequest(getBaseContext());
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
    }

    private class DatabasePutMatchDetailsTask extends AsyncTask<ArrayList<MatchDetails>, Void, ArrayList<MatchDetails>> {
        @Override
        protected ArrayList<MatchDetails> doInBackground(ArrayList<MatchDetails> ... matchDetails) {
            StatDbHelper dbHelper = new StatDbHelper(getBaseContext());
            dbHelper.putMatchDetails(matchDetails[0]);
            return matchDetails[0];
        }
    }

    private class DatabaseReadMatchDetailsTask extends AsyncTask<Void,Void,List<MatchDetails>> {
        @Override
        protected List<MatchDetails> doInBackground(Void... voids) {
            StatDbHelper dbHelper = new StatDbHelper(getBaseContext());
            return dbHelper.readMatchDetailsFromDatabase();
        }

        @Override
        protected void onPostExecute(List<MatchDetails> matchDetails) {
            Log.d(TAG, "Total number of MatchDetails entries read: " + matchDetails.size());
        }
    }
}
