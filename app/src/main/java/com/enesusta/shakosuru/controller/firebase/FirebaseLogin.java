package com.enesusta.shakosuru.controller.firebase;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.enesusta.shakosuru.controller.MyToast;
import com.enesusta.shakosuru.view.LoginScreen;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class FirebaseLogin extends FirebaseInsert implements FirebaseKey {

    private DatabaseReference push;
    private final static String BASE = "Auth/";
    private MyToast myToast;

    private static final String KEY_TITLE = "title";
    private static final String KEY_DESCRIPTION = "description";


    private FirebaseLogin() {

    }

    public FirebaseLogin(Context context) {
        super(context);
        myToast = new MyToast(context);
    }

    public void signUp(String userName, String pass) {

        push = super.getDatabase().getReference().child("Auth").push();

        Map<String, String> auth = new HashMap<>();
        auth.put("User", userName);
        auth.put("Password", pass);

        Map updatedAuth = new HashMap<>();
        updatedAuth.put(BASE + getKey(push), auth);

        super.getDatabase().getReference()
                .updateChildren(updatedAuth, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                        myToast.show("Basarili");
                    }
                });

    }

    public void saveNote(String username, String pass) {

        Map<String, Object> note = new HashMap<>();
        note.put(KEY_TITLE, username);
        note.put(KEY_DESCRIPTION, pass);

        noteRef.set(note)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        myToast.show("Success");

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });

    }





}

