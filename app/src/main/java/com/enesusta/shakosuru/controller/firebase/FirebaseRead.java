package com.enesusta.shakosuru.controller.firebase;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseRead implements ChildEventListener {

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference reference;

    public FirebaseRead() {
        firebaseDatabase = FirebaseDatabase.getInstance();
    }

    public FirebaseRead(String mainKey) {
        firebaseDatabase = FirebaseDatabase.getInstance();
        reference = firebaseDatabase.getReference(mainKey);

    }


    @Override
    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
        Log.i("id",dataSnapshot.getValue().toString());
    }

    @Override
    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
    }

    @Override
    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

    }

    @Override
    public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }
}
