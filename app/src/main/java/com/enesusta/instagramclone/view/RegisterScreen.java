package com.enesusta.instagramclone.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.enesusta.instagramclone.R;
import com.enesusta.instagramclone.controller.Initialize;
import com.enesusta.instagramclone.controller.crypt.Crpyt;
import com.enesusta.instagramclone.controller.firebase.Insert;
import com.enesusta.instagramclone.model.User;
import com.muddzdev.styleabletoast.StyleableToast;

public class RegisterScreen extends AppCompatActivity implements Initialize {

    private EditText emailText,fullNameText,userNameText,passwordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen);
        initComponents();
    }

    public void signUp(View v) {

        String pass = passwordText.getText().toString();
        Crpyt crpyt = new Crpyt(pass);

        User user = User.getInstance();
        user.setPersonEmail(emailText.getText().toString());
        user.setPersonPassword(crpyt.getPass());
        user.setPersonFullName(fullNameText.getText().toString());
        user.setPersonUserName(userNameText.getText().toString());

        Insert insert = new Insert(getApplicationContext());
        insert.execute(user);


        StyleableToast.makeText(getApplicationContext(),"Hello world",R.style.exampleToast);

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
}
