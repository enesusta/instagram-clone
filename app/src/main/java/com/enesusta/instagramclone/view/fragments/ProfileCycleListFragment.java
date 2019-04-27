package com.enesusta.instagramclone.view.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.enesusta.instagramclone.R;

public class ProfileCycleListFragment extends Fragment {


    public ProfileCycleListFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                  Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_profile_list_photos, container, false);
    }





}
