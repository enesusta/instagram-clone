package com.enesusta.instagramclone.activities.daggertut;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.enesusta.instagramclone.R;

import javax.inject.Inject;

public class Dagger extends AppCompatActivity {

    @Inject Car car;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger);





    }





}
