package com.github.randombear.allstatdota.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.github.randombear.allstatdota.R;
import com.github.randombear.allstatdota.adapter.PlayerDetailsAdapter;
import com.github.randombear.allstatdota.dataaccessobject.entities.MatchDetails;

public class MatchDetailsActivity extends AppCompatActivity {
    private static String TAG = "MATCH_DETAILS_ACTIVITY";
    private static String KEY_INTENT_EXTRA = "MATCH_DETAILS_EXTRA";
    private MatchDetails mMatchDetails;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        mMatchDetails = intent.getParcelableExtra(KEY_INTENT_EXTRA);
        Log.d("MATCH_DETAILS", mMatchDetails.toString());
        mRecyclerView = findViewById(R.id.details_recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        PlayerDetailsAdapter adapter = new PlayerDetailsAdapter(mMatchDetails,this);
        mRecyclerView.setAdapter(adapter);
    }
}
