package com.enesusta.shakosuru;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btn;
    EditText editText;
    TextView txt;
    TextView txt1;
    ImageView img;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        actionButton();
        factAction();
    }


    protected void actionButton() {

        btn = findViewById(R.id.buttonOne);
        txt = findViewById(R.id.textOne);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt.setText("Butona tiklandi");
            }
        });

    }

    public void factAction() {

        Button btn2;


        btn2 = findViewById(R.id.buttonTwo);
        txt1 = findViewById(R.id.sonuc);
        editText = findViewById(R.id.editText);

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadImage();
            }
        });

    }

    public int fact(String val) {

        int result = 1;
        for (int i = 1; i < Integer.parseInt(val.trim()); i++)
            result = result * i;

        return result;

    }

    public void uploadImage() {

        // ImageView img;
        img = findViewById(R.id.picOne);

        int rnd = (int) (Math.random() * 3 + 1);
        txt.setText(new Integer(rnd).toString());

        if(rnd == 1)
            img.setImageResource(R.drawable.skype);
        else if(rnd == 2)
            img.setImageResource(R.drawable.cute);
        else
            img.setImageResource(R.drawable.like);



    }


}
