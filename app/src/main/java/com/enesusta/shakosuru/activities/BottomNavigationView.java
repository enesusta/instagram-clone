package com.enesusta.shakosuru.activities;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

public class BottomNavigationView {

    private static final String TAG = "BottomNavigationView";

    public static void setupNav(BottomNavigationViewEx bottomNavigationViewEx) {

        bottomNavigationViewEx.enableAnimation(false);
        bottomNavigationViewEx.enableItemShiftingMode(false);
        bottomNavigationViewEx.enableShiftingMode(false);
        bottomNavigationViewEx.setTextVisibility(false);

    }




}
