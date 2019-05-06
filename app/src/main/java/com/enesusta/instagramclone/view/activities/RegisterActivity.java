package com.enesusta.instagramclone.view.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.enesusta.instagramclone.R;
import com.enesusta.instagramclone.controller.Initialize;
import com.enesusta.instagramclone.controller.Pointer;
import com.enesusta.instagramclone.controller.components.Tool;
import com.enesusta.instagramclone.controller.crypt.Crpyt;
import com.enesusta.instagramclone.controller.firebase.SignUp;
import com.enesusta.instagramclone.model.User;

public class RegisterActivity extends AppCompatActivity implements Initialize, Tool {

    private EditText emailText, fullNameText, userNameText, passwordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen);
        initComponents();
    }

    public void signUp(View v) {

        Crpyt crpyt = new Crpyt();
        String cryptedPass = crpyt.toHash(toChar(passwordText));

        User user = new User();
        user.setPersonEmail(toChar(emailText));
        user.setPersonFullName(toChar(fullNameText));
        user.setPersonUserName(toChar(userNameText));
        user.setPersonPassword(cryptedPass);

        Pointer.putObject("mainUser",user);


        SignUp signUp = new SignUp(getApplicationContext());
        signUp.authentication();



    }


    @Override
    public void initComponents() {
        emailText = findViewById(R.id.registerEmail);
        fullNameText = findViewById(R.id.registerFullName);
        userNameText = findViewById(R.id.registerUsername);
        passwordText = findViewById(R.id.registerPassword);
    }


    @Override
    public void initListeners() {

    }

    private void goMain() {

        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);

    }

    private void sleep() {

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Context getRegisterScreenContext() {
        return this.getApplicationContext();
    }

}
