package com.enesusta.instagramclone.activities.daggertut;

import android.util.Log;

import javax.inject.Inject;

public class PetrolEngine implements Engine {

    private static final String TAG = "CAR";

    @Inject
    public PetrolEngine() {

    }

    @Override
    public void start() {
        Log.d(TAG, "petrol engine started");
    }


}
