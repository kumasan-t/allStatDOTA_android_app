package com.github.randombear.allstatdota.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.randombear.allstatdota.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_layout);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable(){
            @Override
            public void run() {
                // Checks if information about the client is already stored in the shared preferences.
                // If informations are present then the Main Activity launches, otherwise the login
                // activity starts.
                SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.shared_pref_key), Context.MODE_PRIVATE);
                if (sharedPreferences.getString(getString(R.string.shared_pref_key),null) != null) {
                    Intent startLoginActivity = new Intent(SplashActivity.this,MainActivity.class);
                    startLoginActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(startLoginActivity);
                } else {
                    Intent startMainActivity = new Intent(SplashActivity.this, LoginActivity.class);
                    startMainActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(startMainActivity);
                }
            }
        }, 2000);
    }
}
