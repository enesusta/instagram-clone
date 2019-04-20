package com.enesusta.instagramclone.view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.enesusta.instagramclone.R;
import com.enesusta.instagramclone.controller.Initialize;
import com.enesusta.instagramclone.controller.MyToast;
import com.enesusta.instagramclone.controller.PersonList;
import com.enesusta.instagramclone.model.User;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import javax.annotation.Nullable;

public class LoginScreen extends AppCompatActivity implements Initialize {

    private static final String TAG = "LoginScreen";
    private EditText userName, pass;
    private Button login;
    private MyToast myToast;
    private TextView textView;
    private FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    private CollectionReference collectionReference = firebaseFirestore.collection("Users");


    @Override
    protected void onStart() {

        super.onStart();
        collectionReference.addSnapshotListener(this, new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    return;
                }

                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {

                    User user = documentSnapshot.toObject(User.class);
                    user.setPersonId(documentSnapshot.getId());

                    PersonList personList = PersonList.getInstance();
                    personList.add(user);

                }

            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        initComponents();
        signIn();
    }

    @Override
    public void initComponents() {

        userName = findViewById(R.id.userName);
        pass = findViewById(R.id.userPassword);
        login = findViewById(R.id.login);
        myToast = new MyToast(getApplicationContext());
        textView = findViewById(R.id.text_view_data);

    }


    public void signIn() {

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isSignIn())
                    myToast.show("Success");
                else
                    myToast.show("An Error Occured");

            }
        });

    }


    public void transmissionView() {

        Intent intent = new Intent(this, Home.class);
        startActivity(intent);

    }

    public boolean isSignIn() {


        return true;



    }


}