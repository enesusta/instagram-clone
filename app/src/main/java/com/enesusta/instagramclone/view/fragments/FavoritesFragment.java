package com.enesusta.instagramclone.view.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.enesusta.instagramclone.R;
import com.enesusta.instagramclone.controller.components.ViewPagerAdapter;

public class FavoritesFragment extends Fragment {


    private TabLayout tabLayout;
    private ViewPager viewPager;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_favorites, container, false);

        tabLayout = view.findViewById(R.id.tablayout_id);
        viewPager = view.findViewById(R.id.viewpager_id);

        ViewPagerAdapter viewPageAdapter = new ViewPagerAdapter(getFragmentManager());

        viewPageAdapter.addFragment(new FavoritesFragmentTabYou(), "Following");
        viewPageAdapter.addFragment(new FavoritesFragmentTabFollowing(), "You");

        viewPager.setAdapter(viewPageAdapter);
        tabLayout.setupWithViewPager(viewPager);

        return view;
    }


}
