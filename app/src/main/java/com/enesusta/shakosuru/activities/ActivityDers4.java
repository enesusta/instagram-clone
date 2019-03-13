package com.enesusta.shakosuru.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.enesusta.shakosuru.R;

public class ActivityDers4 extends AppCompatActivity {

    TextView tv1,tv2,tv3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ders4);
        func();
        getPost();
    }

    public void func() {
        tv1 = findViewById(R.id.text1);
        tv2 = findViewById(R.id.text2);
        tv3 = findViewById(R.id.text3);
    }

    public void getPost() {

        Bundle bundle = getIntent().getExtras();

        String isim = bundle.getString("isim");
        String soyisim = bundle.getString("soyisim");
        String telNo = bundle.getString("tel");

        tv1.setText(isim);
        tv2.setText(soyisim);
        tv3.setText(telNo);

    }


}
