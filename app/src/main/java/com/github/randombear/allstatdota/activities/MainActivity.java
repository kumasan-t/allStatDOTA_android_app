package com.github.randombear.allstatdota.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.github.randombear.allstatdota.R;
import com.github.randombear.allstatdota.dataaccessobject.entities.MatchHistory;
import com.github.randombear.allstatdota.dataaccessobject.interfaces.VolleyCallback;
import com.github.randombear.allstatdota.dataaccessobject.remote.DotaDataRequest;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DotaDataRequest dotaDataRequest = new DotaDataRequest(this.getBaseContext());
        dotaDataRequest.getMatchHistory(new VolleyCallback() {
            @Override
            public void onSuccessResponse(JSONObject result) {
                    MatchHistory matchHistory = MatchHistory.createFromJSON(result);
                    TextView textView = (TextView) findViewById(R.id.textView);
                    textView.setText(matchHistory.toString());
                }
            }
        );

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
