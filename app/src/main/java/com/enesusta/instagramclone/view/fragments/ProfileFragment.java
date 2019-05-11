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
import com.enesusta.instagramclone.controller.Pointer;
import com.enesusta.instagramclone.controller.ViewPagerAdapter;
import com.enesusta.instagramclone.controller.annotations.Metadata;
import com.enesusta.instagramclone.controller.enums.Priority;
import com.enesusta.instagramclone.controller.enums.Type;
import com.enesusta.instagramclone.model.User;
import com.enesusta.instagramclone.view.activities.EditProfileActivity;

/*

MIT License

Copyright (c) 2019 Enes Usta

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

 */

@Metadata(
        priority = Priority.MEDIUM,
        type = Type.VIEW,
        author = "Enes Usta",
        lastModified = "05/05/2019"
)


public class ProfileFragment extends Fragment {

    private TextView profileUserText;
    private Button editProfileButton;
    private TabLayout tabLayout;
    private ViewPager viewPager;


    private User user = (User) Pointer.getObject("user");


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        init(view);
        editProfile(view);

        return view;

    }

    private void init(View view) {

        profileUserText = view.findViewById(R.id.profile_user_text);
        profileUserText.setText(user.getPersonFullName());

        tabLayout = view.findViewById(R.id.tablayout_profile_id);
        viewPager = view.findViewById(R.id.viewpager_profile_id);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getFragmentManager(),true);
        viewPagerAdapter.addFragment(new ProfileCyclePhotosFragment(),"tab1");
        viewPagerAdapter.addFragment(new ProfileCycleListFragment(),"tab2");

        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }



    private void editProfile(View v) {

        Intent intent = new Intent(v.getContext(), EditProfileActivity.class);
        editProfileButton = v.findViewById(R.id.profile_settings_edit_profile_button);

        editProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });
    }



}
