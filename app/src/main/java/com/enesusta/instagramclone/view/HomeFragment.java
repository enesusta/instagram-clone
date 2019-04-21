package com.enesusta.instagramclone.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.enesusta.instagramclone.R;
import com.enesusta.instagramclone.activities.ExampleAdapter;
import com.enesusta.instagramclone.activities.ExampleItem;
import com.enesusta.instagramclone.controller.Initialize;

import java.util.ArrayList;
import java.util.List;

/*
 * @author : Enes Usta
 */


public class HomeFragment extends Fragment implements Initialize {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_home, container, false);

        List<ExampleItem> exampleItems = new ArrayList<>();
        exampleItems.add(new ExampleItem(R.drawable.camera, "Line1", "Line2"));
        exampleItems.add(new ExampleItem(R.drawable.comment, "Line1", "Line2"));
        exampleItems.add(new ExampleItem(R.drawable.insta, "Line1", "Line2"));
        exampleItems.add(new ExampleItem(R.drawable.camera, "Line1", "Line2"));
        exampleItems.add(new ExampleItem(R.drawable.camera, "Line1", "Line2"));
        exampleItems.add(new ExampleItem(R.drawable.comment, "Line1", "Line2"));
        exampleItems.add(new ExampleItem(R.drawable.insta, "Line1", "Line2"));
        exampleItems.add(new ExampleItem(R.drawable.comment, "Line1", "Line2"));
        exampleItems.add(new ExampleItem(R.drawable.insta, "Line1", "Line2"));
        exampleItems.add(new ExampleItem(R.drawable.comment, "Line1", "Line2"));
        exampleItems.add(new ExampleItem(R.drawable.insta, "Line1", "Line2"));
        exampleItems.add(new ExampleItem(R.drawable.comment, "Line1", "Line2"));
        exampleItems.add(new ExampleItem(R.drawable.insta, "Line1", "Line2"));
        exampleItems.add(new ExampleItem(R.drawable.insta, "Line1", "Line2"));
        exampleItems.add(new ExampleItem(R.drawable.insta, "Line1", "Line2"));
        exampleItems.add(new ExampleItem(R.drawable.insta, "Line1", "Line2"));
        exampleItems.add(new ExampleItem(R.drawable.comment, "Line1", "Line2"));
        exampleItems.add(new ExampleItem(R.drawable.insta, "Line1", "Line2"));
        exampleItems.add(new ExampleItem(R.drawable.comment, "Line1", "Line2"));
        exampleItems.add(new ExampleItem(R.drawable.comment, "Line1", "Line2"));
        exampleItems.add(new ExampleItem(R.drawable.insta, "Line1", "Line2"));
        exampleItems.add(new ExampleItem(R.drawable.comment, "Line1", "Line2"));
        exampleItems.add(new ExampleItem(R.drawable.comment, "Line1", "Line2"));
        exampleItems.add(new ExampleItem(R.drawable.insta, "Line1", "Line2"));
        exampleItems.add(new ExampleItem(R.drawable.comment, "Line1", "Line2"));


        recyclerView = v.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(v.getContext());
        mAdapter = new ExampleAdapter(exampleItems);

        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);


        return v;

    }

    public void onClick(View v) {
        Toast.makeText(v.getContext(), "Deneme", Toast.LENGTH_SHORT);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

    }

    @Override
    public void initComponents() {
    }

    @Override
    public void initListeners() {

    }
}
