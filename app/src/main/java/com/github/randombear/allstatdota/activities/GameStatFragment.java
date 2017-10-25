package com.github.randombear.allstatdota.activities;

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
import android.widget.TextView;

import com.github.randombear.allstatdota.R;
import com.github.randombear.allstatdota.dataaccessobject.entities.MatchDetails;
import com.github.randombear.allstatdota.dataaccessobject.local.StatDbHelper;
import com.github.randombear.allstatdota.event.MessageEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

public class GameStatFragment extends Fragment {

    private static final String TAG = "GameStat Fragment";

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private MatchDetails[] mDetailedMatchList;

    public GameStatFragment() {
        // Required empty public constructor
    }

    public static GameStatFragment newInstance() {
        GameStatFragment fragment = new GameStatFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_game_stat, container, false);

        mRecyclerView = rootView.findViewById(R.id.content_gamestat_recyclerView);
        mSwipeRefreshLayout = rootView.findViewById(R.id.content_gamestat_swipeRefresh);
        //Using a Linear Layout Manager
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addView(new TextView(getContext()));
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                AsyncTask<Void,Void,List<MatchDetails>> dbRead = new DatabaseReadMatchDetailsTask();
                dbRead.execute();
            }
        });
        return rootView;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handleMessageEvent(MessageEvent event) {
        mDetailedMatchList = event.message;
    }
    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        Log.d(TAG,"Stopped");
        super.onStop();
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
        }
    }

}
