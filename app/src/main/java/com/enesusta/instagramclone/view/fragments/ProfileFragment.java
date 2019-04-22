package com.enesusta.instagramclone.view.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.enesusta.instagramclone.R;
import com.enesusta.instagramclone.controller.Initialize;
import com.enesusta.instagramclone.controller.Pointer;
import com.enesusta.instagramclone.controller.components.ViewPagerAdapter;
import com.enesusta.instagramclone.model.User;
import com.enesusta.instagramclone.view.activities.EditProfileActivity;

/*
 * @author : Enes Usta
 *
 */


public class ProfileFragment extends Fragment implements Initialize {

    private TextView profileUserText;
    private Button editProfileButton;
    private TabLayout tabLayout;
    private ViewPager viewPager;


    private User user = (User) Pointer.getObject("user");



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        profileUserText = view.findViewById(R.id.profile_user_text);
        profileUserText.setText(user.getPersonUserName());

        tabLayout = view.findViewById(R.id.tablayout_profile_id);
        viewPager = view.findViewById(R.id.viewpager_profile_id);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getFragmentManager());
        viewPagerAdapter.addFragment(new ProfileCyclePhotosFragment());
        viewPagerAdapter.addFragment(new ProfileCycleListFragment());

        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        setIcons(tabLayout);


        editProfileButton = view.findViewById(R.id.profile_settings_edit_profile_button);
        editProfile(view);

        return view;

    }

    @Override
    public void initComponents() {

    }

    @Override
    public void initListeners() {

    }

    private void editProfile(View v) {

        Intent intent = new Intent(v.getContext(), EditProfileActivity.class);

        editProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });
    }

    private void setIcons(TabLayout tabLayout) {

        int[] arr = {
                R.drawable.comment,
                R.drawable.comment
        };

        for(int i=0;i<arr.length;i++)
            tabLayout.getTabAt(i).setIcon(arr[i]);

    }



}
