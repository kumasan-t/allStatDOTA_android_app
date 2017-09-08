package com.github.randombear.allstatdota.application;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * =================================
 * Created by randomBEAR on 08/09/2017.
 * =================================
 */

public class AllStatDotaApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }
}
