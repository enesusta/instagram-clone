package com.enesusta.instagramclone.activities;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.enesusta.instagramclone.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ActivityDers7 extends AppCompatActivity {

    EditText isim, yas, numara, takim;
    Button ekle;
    FirebaseDatabase database;
    DatabaseReference ref;
    String mainChild, key;
    RecyclerView recyclerView;
    List<User> list;
    UserAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ders7);
        initComponents();
        action();
        load();
    }

    void initComponents() {

        list = new ArrayList<>();
        isim = findViewById(R.id.isim);
        yas = findViewById(R.id.yas);
        numara = findViewById(R.id.numara);
        takim = findViewById(R.id.takim);
        ekle = findViewById(R.id.ekle);
        database = FirebaseDatabase.getInstance();
        ref = database.getReference();
        mainChild = "Users/";
        recyclerView = findViewById(R.id.recyleView);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        adapter = new UserAdapter(list,getApplicationContext());
        recyclerView.setAdapter(adapter);


    }

    public void action() {

        ekle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatabaseReference ref2 = ref.child("Users").push();
                key = ref2.getKey();

                Map mp = setMessage(isim.getText().toString(), yas.getText().toString(), numara.getText().toString(), takim.getText().toString());

                Map mp2 = new HashMap();
                mp2.put(mainChild + key, mp);
                ref.updateChildren(mp2, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                        reset();
                        Toast.makeText(getApplicationContext(), "Kayit basari ile eklendi", Toast.LENGTH_LONG).show();
                    }
                });


            }
        });
    }


    public Map setMessage(String isim, String yas, String numara, String takim) {

        Map map = new HashMap();
        map.put("isim", isim);
        map.put("yas", yas);
        map.put("takim", takim);
        map.put("numara", numara);

        return map;
    }

    public void reset() {

        isim.setText("");
        yas.setText("");
        numara.setText("");
        takim.setText("");

    }


    public void load() {

        ref.child("Users").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                User user = dataSnapshot.getValue(User.class);
                adapter.notifyDataSetChanged();
                list.add(user);

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
        });

    }


}
