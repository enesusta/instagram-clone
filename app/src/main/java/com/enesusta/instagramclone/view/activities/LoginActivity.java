package com.enesusta.instagramclone.view.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.enesusta.instagramclone.R;
import com.enesusta.instagramclone.controller.Initialize;
import com.enesusta.instagramclone.controller.MyToast;
import com.enesusta.instagramclone.controller.PersonList;
import com.enesusta.instagramclone.controller.Pointer;
import com.enesusta.instagramclone.controller.Tool;
import com.enesusta.instagramclone.controller.crypt.Crpyt;
import com.enesusta.instagramclone.controller.firebase.CommentManager;
import com.enesusta.instagramclone.controller.firebase.CommentService;
import com.enesusta.instagramclone.controller.firebase.CommentServiceImp;
import com.enesusta.instagramclone.controller.firebase.LoginService;
import com.enesusta.instagramclone.controller.firebase.LoginManager;
import com.enesusta.instagramclone.controller.firebase.SignIn;
import com.enesusta.instagramclone.model.User;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import javax.annotation.Nullable;

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


public class LoginActivity extends AppCompatActivity implements Initialize, Tool {

    private static final String TAG = "LoginActivity";
    private EditText userName, pass;
    private Button login;
    private MyToast myToast;
    private TextView textView;
    private FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    private CollectionReference collectionReference = firebaseFirestore.collection("Users");
    private TextView signText;
    private boolean isNewRecord = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        initComponents();
        initListeners();

    }


    @Override
    public void initComponents() {

        userName = findViewById(R.id.userName);
        pass = findViewById(R.id.userPassword);
        login = findViewById(R.id.login);
        myToast = new MyToast(getApplicationContext());
        textView = findViewById(R.id.text_view_data);
        signText = findViewById(R.id.sign_up_text);
    }

    @Override
    public void initListeners() {


        login.setOnClickListener(v -> {

            Crpyt crpyt = new Crpyt();
            String crypedPass = crpyt.toHash(toChar(pass));

            User user = new User();
            user.setPersonEmail(toChar(userName));
            user.setPersonPassword(crypedPass);

            if (isNewRecord)
                isNewRecord = false;
            else
                Pointer.putObject("mainUser", user);

            LoginService signIn = new SignIn(getApplicationContext());

            LoginManager signManager = new LoginManager(signIn);
            signManager.authentication();

        });

        signText.setOnClickListener(v -> {
            isNewRecord = true;
            goRegister();
        });

    }


    private void goRegister() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }


    private void goHome() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }


}
