package com.enesusta.instagramclone.controller.components;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private boolean status;

    private final List<Fragment> fragmentList = new ArrayList<>();
    private final List<String> FragmentListTitles = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager fm,boolean status) {
        super(fm);
        this.status = status;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return FragmentListTitles.size();
    }

    public CharSequence getPageTitle(int position) {
        if(status) return FragmentListTitles.get(position);
        else return null;
    }


    public void addFragment(Fragment fragment, String Title) {
        fragmentList.add(fragment);
        FragmentListTitles.add(Title);
    }

    public void addFragment(Fragment fragment) {
        fragmentList.add(fragment);
    }



}
