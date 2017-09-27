package com.github.randombear.allstatdota.activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.github.randombear.allstatdota.R;
import com.github.randombear.allstatdota.adapter.MatchAdapter;
import com.github.randombear.allstatdota.dataaccessobject.entities.MatchHistory;
import com.github.randombear.allstatdota.dataaccessobject.local.StatDbHelper;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.content_main_recyclerView);

        //Using a Linear Layout Manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        DatabaseReadTask databaseReadTask = new DatabaseReadTask();
        databaseReadTask.execute();
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

    private class DatabaseDeleteTask extends AsyncTask<Void,Void,Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            getBaseContext().deleteDatabase("dotaStat.db");
            return null;
        }
    }

    private class DatabasePopulateTask extends AsyncTask<MatchHistory,Void,Void> {
        @Override
        protected Void doInBackground(MatchHistory... matchHistories) {
            StatDbHelper dbHelper = new StatDbHelper(getBaseContext());
            for (MatchHistory m : matchHistories) {
                dbHelper.populateDatabase(m);
            }
            return null;
        }
    }
}
