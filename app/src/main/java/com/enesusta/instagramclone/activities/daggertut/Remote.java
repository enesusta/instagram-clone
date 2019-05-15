package com.enesusta.instagramclone.activities.daggertut;

import android.util.Log;

import javax.inject.Inject;

class Remote {

    @Inject
    public Remote() {

    }

    public void setListener(Car car) {
        Log.d("CAR","Remote connected");
    }

}
