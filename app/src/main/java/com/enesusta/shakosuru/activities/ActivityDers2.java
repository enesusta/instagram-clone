package com.enesusta.shakosuru.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.enesusta.shakosuru.R;

public class ActivityDers2 extends AppCompatActivity {

    String kadi;
    String ksifre;
    private static final String TAG = "ActivityDers2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ders2);
        getDeger();
    }

    public void getDeger() {

        Bundle bundle = getIntent().getExtras();
        kadi = bundle.getString("KullaniciAdi");
        ksifre = bundle.getString("kullaniciSifre");
        Log.i("Values : ", kadi + " " + ksifre );

    }
}
