package com.enesusta.shakosuru.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.enesusta.shakosuru.R;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    EditText kadi,ksifre;
    RadioGroup radioGroup;
    String isim,sifre,kullaniciCinsiyet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate:starting.");
        gecis();
    }


    public void gecis() {

        Button button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getValues();
                Intent intent = new Intent(getApplicationContext(),ActivityDers2.class);
                intent.putExtra("KullaniciAdi",isim);
                intent.putExtra("kullaniciSifre",sifre);
                startActivity(intent);
            }
        });

    }

    public void runA() {

        //main activity, gecis yapacagin activity

        Intent intent = new Intent(this, ActivityDers1.class);
        startActivity(intent);
    }

    public void getValues() {

        kadi = findViewById(R.id.editTextKullanici);
        ksifre = findViewById(R.id.editTextSifre);
        radioGroup = findViewById(R.id.cinsiyetGrup);

        isim = kadi.getText().toString();
        sifre = ksifre.getText().toString();


    }



















































}
