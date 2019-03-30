package com.enesusta.shakosuru.controller.firebase;

import android.content.Context;
import android.support.annotation.NonNull;

import com.enesusta.shakosuru.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.annotation.Inherited;
import java.util.HashMap;
import java.util.Map;

public class FirebaseInsert extends Firebase implements IConnectable {

    private FirebaseDatabase database;
    private DatabaseReference reference;
    private User user;
    private boolean connectionStatus;
    private String refenceString;

    public FirebaseInsert() {

    }

    public FirebaseInsert(Context context) {
        super(context);
        initComponents();
        connectionStatus = true;

    }

    public FirebaseInsert(User user) {

        this.user = user;
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("Users/enes");

    }

    public FirebaseInsert(Context context,String refenceString) {
        super(context);
        this.refenceString = refenceString;
        database = FirebaseDatabase.getInstance();
        reference = database.getReference(refenceString);

    }



    public void execute() {

        Map<String, String> userMap = new HashMap<>();

        userMap.put("Id", user.getId());
        userMap.put("Isim", user.getName());
        userMap.put("Soyisim", user.getLastName());
        userMap.put("Yas", String.valueOf(user.getAge()));

        reference.setValue(userMap);
    }


    @Override
    public void initComponents() {
        database = FirebaseDatabase.getInstance();
    }

    @Override
    public boolean isConnected() {


        reference = database.getReference(".info/connected");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                boolean connected = dataSnapshot.getValue(Boolean.class);
                setConnectionStatus(connected);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {


            }
        });

        return connectionStatus;

    }

    public boolean isConnectionStatus() {
        return connectionStatus;
    }

    public void setConnectionStatus(boolean connectionStatus) {
        this.connectionStatus = connectionStatus;
    }

    public FirebaseDatabase getDatabase() {
        return database;
    }

    public void setDatabase(FirebaseDatabase database) {
        this.database = database;
    }

    public String getRefenceString() {
        return refenceString;
    }

    public void setRefenceString(String refenceString) {
        this.refenceString = refenceString;
    }
}
