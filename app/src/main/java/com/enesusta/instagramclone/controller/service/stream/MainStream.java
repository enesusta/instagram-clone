package com.enesusta.instagramclone.controller.service.stream;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.enesusta.instagramclone.R;
import com.enesusta.instagramclone.controller.Pointer;
import com.enesusta.instagramclone.controller.annotations.Metadata;
import com.enesusta.instagramclone.controller.enums.Priority;
import com.enesusta.instagramclone.controller.enums.Type;
import com.enesusta.instagramclone.controller.recycler.StreamAdapter;
import com.enesusta.instagramclone.model.Comment;
import com.enesusta.instagramclone.model.Upload;
import com.enesusta.instagramclone.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

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

@Metadata(
        priority = Priority.HIGH,
        type = Type.CONTROLLER,
        author = "Enes Usta",
        lastModified = "14/05/2019"
)
public class MainStream implements StreamService {

    private FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference;
    private CollectionReference collectionReference = firebaseFirestore.collection("Users");

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    protected User user = (User) Pointer.getObject("user");
    private List<Upload> uploads;
    private List<Comment> comments;

    private void init(View v) {

        uploads = new ArrayList<>();
        recyclerView = v.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(v.getContext()));
    }


    @Override
    public void flow(View v) {

        init(v);

        collectionReference.get()
           .addOnSuccessListener(queryDocumentSnapshots -> {

            for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {

                      CollectionReference collectionReference =
                                firebaseFirestore
                                        .collection("Users")
                                        .document(documentSnapshot.getId())
                                        .collection("Photos");


                        databaseReference = firebaseDatabase
                                .getReference("Users")
                                .child(documentSnapshot.getId())
                                .child("Content");

                        databaseReference.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                    Upload upload = snapshot.getValue(Upload.class);
                                    uploads.add(upload);
                                }

                                mAdapter = new StreamAdapter(uploads, v.getContext());
                               recyclerView.setAdapter(mAdapter);

                        }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


            }
        });

    }
}
