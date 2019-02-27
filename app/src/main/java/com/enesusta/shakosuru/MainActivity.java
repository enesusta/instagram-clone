package com.enesusta.shakosuru;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    List<UserPhotoModel> userPhotoModels;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    PhotoAdapter photoAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inserList();

    }

    public void inserList() {

        userPhotoModels = new ArrayList<>();
        recyclerView = findViewById(R.id.recycle);
        layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);

        userPhotoModels.add(new UserPhotoModel("Enes","Usta",21,R.drawable.comment));
        userPhotoModels.add(new UserPhotoModel("Enec","Usta",21,R.drawable.cute));
        userPhotoModels.add(new UserPhotoModel("Enex","Usta",21,R.drawable.group));
        userPhotoModels.add(new UserPhotoModel("Enek","Usta",21,R.drawable.share));
        photoAdapter = new PhotoAdapter(this,userPhotoModels);

        recyclerView.setAdapter(photoAdapter);

    }

}
