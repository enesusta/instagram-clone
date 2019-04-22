package com.enesusta.instagramclone.view.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.enesusta.instagramclone.R;
import com.enesusta.instagramclone.controller.Initialize;
import com.enesusta.instagramclone.controller.Pointer;
import com.enesusta.instagramclone.model.User;

import java.util.Map;

/*
 * @author : Enes Usta
 *
 */


public class ProfileFragment extends Fragment implements Initialize {

    private TextView profileUserText;

    private User user = (User) Pointer.getObject("user");

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        profileUserText = view.findViewById(R.id.profile_user_text);
        profileUserText.setText(user.getPersonUserName());


        return view;
    }

    @Override
    public void initComponents() {


    }

    @Override
    public void initListeners() {

    }


}
