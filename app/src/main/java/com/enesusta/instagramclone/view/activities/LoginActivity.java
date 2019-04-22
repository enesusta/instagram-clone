package com.enesusta.instagramclone.view.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.enesusta.instagramclone.R;
import com.enesusta.instagramclone.controller.Initialize;
import com.enesusta.instagramclone.controller.MyToast;
import com.enesusta.instagramclone.controller.PersonList;
import com.enesusta.instagramclone.controller.Pointer;
import com.enesusta.instagramclone.controller.components.Tool;
import com.enesusta.instagramclone.controller.crypt.Crpyt;
import com.enesusta.instagramclone.controller.firebase.SignIn;
import com.enesusta.instagramclone.model.User;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import javax.annotation.Nullable;

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


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Crpyt crpyt = new Crpyt();
                String crypedPass = crpyt.toHash(toChar(pass));

                User user = new User();
                user.setPersonEmail(toChar(userName));
                user.setPersonPassword(crypedPass);

                User tempUser = null;

                if (isNewRecord) {
                    // TODO : DO NOTHING
                } else {
                    Pointer.putObject("mainUser", user);
                }

                SignIn signIn = new SignIn(getApplicationContext());
                signIn.authentication();

            }
        });

        signText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isNewRecord = true;
                goRegister();
            }
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
