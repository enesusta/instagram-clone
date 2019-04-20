package com.enesusta.instagramclone.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.enesusta.instagramclone.R;

public class ActivityDers1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ders1);
        gecis();

    }

    public void runA() {

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

    public void gecis() {

        Button button = findViewById(R.id.button1);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runA();
            }
        });

    }


}
