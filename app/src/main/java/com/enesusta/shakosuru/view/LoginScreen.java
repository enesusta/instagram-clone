package com.enesusta.shakosuru.view;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.enesusta.shakosuru.R;
import com.enesusta.shakosuru.activities.MainActivity;
import com.enesusta.shakosuru.controller.MyToast;
import com.enesusta.shakosuru.controller.NullGrip;
import com.enesusta.shakosuru.controller.firebase.Firebase;
import com.enesusta.shakosuru.controller.firebase.FirebaseLogin;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class LoginScreen extends AppCompatActivity {

    private EditText userName, pass;
    private Button login;
    private FirebaseAuth auth;
    private MyToast myToast;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        initComponents();
        action();
    }

    private void initComponents() {

        userName = findViewById(R.id.userName);
        pass = findViewById(R.id.userPassword);
        login = findViewById(R.id.login);
        auth = FirebaseAuth.getInstance();
        myToast = new MyToast(getApplicationContext());
        textView = findViewById(R.id.text_view_data);

    }

    public void signUp(String user, String pass) {

        auth.createUserWithEmailAndPassword(user, pass).addOnCompleteListener(this, task -> {

            if (task.isSuccessful())
                myToast.show("Sign up successfully");
            else
                myToast.show("An error occured");

        });

    }

    private void action() {

        login.setOnClickListener(v -> {

            NullGrip nullGrip = str -> {
                return str != null ? true : false;
            };

            FirebaseLogin firebaseLogin = new FirebaseLogin(getApplicationContext());

            if (nullGrip.isNull(userName.getText().toString())
                    & nullGrip.isNull(pass.getText().toString())) {
                firebaseLogin.saveNote
                        (userName.getText().toString(), pass.getText().toString());
                transmissionView();
            } else
                myToast.show("An error occurred2");

        });

    }

    public void transmissionView() {

        Intent intent = new Intent(this, Home.class);
        startActivity(intent);

    }

    public void loadNote() {


        noteRef.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            String title = documentSnapshot.getString("title");
                            String description = documentSnapshot.getString("description");

                        }else {
                            myToast.show("Document doesnt exist");
                        }

                    }
                });


    }
}
