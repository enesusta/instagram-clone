package com.enesusta.shakosuru.controller.firebase;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.enesusta.shakosuru.activities.ActivityDers6;
import com.enesusta.shakosuru.controller.LocalDate;
import com.enesusta.shakosuru.controller.MyToast;
import com.enesusta.shakosuru.model.User;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

public class FirebaseMessageInsert extends FirebaseInsert implements LocalDate {


    private final static String BASE = "messages/";
    private DatabaseReference reference;
    private DatabaseReference push;
    private Context context;
    private MyToast myToast;


    public FirebaseMessageInsert(User user) {
        super(user);
    }

    public FirebaseMessageInsert(User user, Context context) {
        super(user);
        this.context = context;

    }

    @Override
    public void initComponents() {

    }


    @Override
    public void execute() {

        if (super.isConnected()) {
            push = super.getDatabase().getReference().child("message").push();

            Map<String, String> message = new HashMap<>();
            message.put("Content", "Merhaba2");
            message.put("Date", getCurrentLocalDate());

            Map updatedMessage = new HashMap();
            updatedMessage.put(BASE + getMessageKey(), message);


            super.getDatabase().getReference().updateChildren(updatedMessage, new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {

                    MyToast myToast = new MyToast(context);
                    myToast.show("Basarili");

                }
            });

        } else {


        }

    }

    public void setMainContext(Context context) {
        this.context = context;
    }

    public String getMessageKey() {
        return push.getKey();
    }


}
