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
import com.enesusta.instagramclone.controller.Tool;
import com.enesusta.instagramclone.controller.crypt.Crpyt;
import com.enesusta.instagramclone.controller.firebase.LoginService;
import com.enesusta.instagramclone.controller.firebase.LoginManager;
import com.enesusta.instagramclone.controller.firebase.SignUp;
import com.enesusta.instagramclone.model.User;

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

        LoginService signUp = new SignUp(getApplicationContext());
        LoginManager signManager = new LoginManager(signUp);
        signManager.authentication();

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

    private void switchToRegisterActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

}
