package com.github.randombear.allstatdota.activities;

import android.content.Intent;
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
                Intent startMainActivity = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(startMainActivity);
            }
        }, 3000);
    }
}
