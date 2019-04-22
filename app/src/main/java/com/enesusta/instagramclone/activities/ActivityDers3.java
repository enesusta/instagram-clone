package com.enesusta.instagramclone.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.enesusta.instagramclone.R;

import java.util.ArrayList;
import java.util.List;

public class ActivityDers3 extends AppCompatActivity {


    List<UserModel> list;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ders3);
        createList();

    }

    public void createList() {

        list = new ArrayList<>();
        listView = findViewById(R.id.listView);
        adapterModel adapterModel = new adapterModel(list,this,this);
        listView.setAdapter(adapterModel);


        UserModel model = new UserModel("Enes","Usta",13214124);
        UserModel model2 = new UserModel("Anes","Usta",13214124);
        UserModel model3 = new UserModel("Snes","Usta",13214124);
        UserModel model4 = new UserModel("Xnes","Usta",13214124);

        list.add(model);
        list.add(model2);
        list.add(model3);
        list.add(model4);

        list.add(model3);
        list.add(model3);
        list.add(model3);
        list.add(model3);
        list.add(model3);
        list.add(model3);
        list.add(model3);
        list.add(model3);
        list.add(model3);
        list.add(model3);
        list.add(model3);
        list.add(model3);
        list.add(model4);
        list.add(model4);
        list.add(model4);
        list.add(model4);
        list.add(model4);
        list.add(model4);
        list.add(model4);
        list.add(model4);
        list.add(model4);
        list.add(model4);
        list.add(model4);
        list.add(model4);

    }








}
