package com.github.randombear.allstatdota.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.github.randombear.allstatdota.R;
import com.github.randombear.allstatdota.dataaccessobject.entities.MatchDetails;

public class MatchDetailsActivity extends AppCompatActivity {
    private static String TAG = "MATCH_DETAILS_ACTIVITY";
    private static String KEY_INTENT_EXTRA = "MATCH_DETAILS_EXTRA";
    private MatchDetails mMatchDetails;
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        mMatchDetails = intent.getParcelableExtra(KEY_INTENT_EXTRA);
        Log.d(TAG,  mMatchDetails.toString());
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}
