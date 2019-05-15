package com.enesusta.instagramclone.activities.daggertut;

import android.util.Log;

import javax.inject.Inject;

public class DieselEngine implements Engine {

    private static final String TAG = "CAR";

    @Inject
    public DieselEngine() {
        
    }

    @Override
    public void start() {
        Log.d(TAG, "Diesel engine started");
    }


}
