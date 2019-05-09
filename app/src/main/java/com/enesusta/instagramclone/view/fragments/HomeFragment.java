package com.enesusta.instagramclone.view.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.enesusta.instagramclone.R;
import com.enesusta.instagramclone.controller.Initialize;
import com.enesusta.instagramclone.controller.Pointer;
import com.enesusta.instagramclone.model.Comment;
import com.enesusta.instagramclone.model.Upload;
import com.enesusta.instagramclone.model.User;
import com.enesusta.instagramclone.view.recycler.CommentAdapter;
import com.enesusta.instagramclone.view.recycler.StreamAdapter;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


/*

MIT License

Copyright (c) 2019 Enes Usta

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

 */


public class HomeFragment extends Fragment implements Initialize {


    private FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference;
    private CollectionReference collectionReference = firebaseFirestore.collection("Users");

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private RecyclerView commentRecyclerView;
    private RecyclerView.Adapter commentAdapter;
    private RecyclerView.LayoutManager commentManager;

    protected User user = (User) Pointer.getObject("user");
    private List<Upload> uploads;
    private List<Comment> comments;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_home, container, false);
        prepareView(v);
       // prepareComment(v);
        return v;

    }

    private void prepareComment(View v) {

        comments = new ArrayList<>();
        //commentRecyclerView = v.findViewById(R.id.stream_recycler_view);
        commentRecyclerView.setHasFixedSize(true);
        commentRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));

        commentAdapter = new CommentAdapter(comments,getActivity().getApplicationContext());
        commentRecyclerView.setAdapter(commentAdapter);
    }


    private void prepareView(View v) {


        uploads = new ArrayList<>();
        recyclerView = v.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));


        collectionReference.get()

                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {

                            CollectionReference collectionReference =
                                    firebaseFirestore
                                            .collection("Users")
                                            .document(documentSnapshot.getId())
                                            .collection("Photos");


                            databaseReference = firebaseDatabase
                                    .getReference("Users")
                                    .child(documentSnapshot.getId())
                                    .child("Photos");

                            databaseReference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                        Upload upload = snapshot.getValue(Upload.class);
                                        uploads.add(upload);
                                    }

                                    mAdapter = new StreamAdapter(uploads,getActivity().getApplicationContext());
                                    recyclerView.setAdapter(mAdapter);

                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });


                        }
                    }
                });


    }



    public void onClick(View v) {
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

    }

    @Override
    public void initComponents() {

    }


    @Override
    public void initListeners() {

    }
}
