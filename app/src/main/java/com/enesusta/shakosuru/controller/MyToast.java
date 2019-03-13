package com.enesusta.shakosuru.controller;

import android.content.Context;
import android.widget.Toast;

public class MyToast {

    private Context context;

    public MyToast(Context context) {
        this.context = context;
    }

    public void show(String text) {
        Toast.makeText(context,text,Toast.LENGTH_SHORT).show();
    }


}
