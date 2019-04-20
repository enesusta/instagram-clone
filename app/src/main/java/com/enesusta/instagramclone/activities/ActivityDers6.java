package com.enesusta.instagramclone.activities;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.enesusta.instagramclone.R;
import com.enesusta.instagramclone.controller.crypt.ICrypt;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ActivityDers6 extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference ref1;
    EditText key, value;
    Button button;
    TextView txt1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ders6);
        initComponents();
        actionRealtimeDatabase();
        //addChild();
        add();

    }

    void add() {

 /*
        User user = new User();
        user.setId("13214");
        user.setName("Enes");
        user.setLastName("Usta");
        user.setAge(21);

        FirebaseInsert firebaseInsert = new FirebaseInsert(user);

        firebaseInsert.execute();

        */


    }


    public void initComponents() {

        database = FirebaseDatabase.getInstance();
        key = findViewById(R.id.key);
        value = findViewById(R.id.value);
        button = findViewById(R.id.ekle);
        txt1 = findViewById(R.id.result);


    }

    public void actionRealtimeDatabase() {

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatabaseReference reference = database.getReference(key.getText().toString());
                reference.setValue(value.getText().toString());
                key.setText("");
                value.setText("");

            }
        });


    }

    public void showValue() {

        ref1 = database.getReference("user1");

        ref1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                txt1.setText(dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    void addChild() {

        ICrypt crypt = new ICrypt();
        DatabaseReference id, isim, soyisim;
        database = FirebaseDatabase.getInstance();

        String us = "user1";


        ref1 = database.getReference(crypt.getMD5EncryptedString(us));
        id = ref1.child("userId");
        id.setValue("1");

        isim = ref1.child("isim");
        isim.setValue("Enes");

        soyisim = ref1.child("sooyisim");
        soyisim.setValue("ustsa");

    }

    void readDataFromFirebaseDatabase(String mainKey) {

    }


}
