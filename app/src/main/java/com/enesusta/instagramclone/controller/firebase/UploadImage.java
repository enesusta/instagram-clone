package com.enesusta.instagramclone.controller.firebase;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.enesusta.instagramclone.controller.Initialize;
import com.enesusta.instagramclone.controller.NullGrip;
import com.enesusta.instagramclone.controller.Pointer;
import com.enesusta.instagramclone.controller.Tool;
import com.enesusta.instagramclone.model.Upload;
import com.enesusta.instagramclone.model.User;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.apache.commons.math3.random.RandomData;
import org.apache.commons.math3.random.RandomDataImpl;

import static android.content.ContentValues.TAG;

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


public class UploadImage implements Content, Initialize, Tool {

    private FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
    private FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private StorageReference storageReference;
    private DatabaseReference databaseReference;
    private DocumentReference documentReference;
    private Uri uri;
    private Context context;
    protected User user = (User) Pointer.getObject("user");


    public UploadImage(Uri uri, Context context) {
        this.uri = uri;
        this.context = context;
        initComponents();
    }


    @Override
    public void uploadContent() {

        NullGrip<Uri> uriCheck = element -> {
            return element != null ? true : false;
        };


        if (uriCheck.isNull(uri)) {


            storageReference.putFile(uri).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if (!task.isSuccessful()) {
                        throw task.getException();
                    }
                    return storageReference.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {

                    if (task.isSuccessful()) {

                        Uri downloadUri = task.getResult();

                        Toast.makeText(context,
                                "Upload succesful", Toast.LENGTH_SHORT).show();

                        Upload upload = new Upload("", downloadUri.toString());
                        upload.setUserName(user.getPersonUserName());

//                        databaseReference.push().setValue(upload);
                        String key = databaseReference.push().getKey();

                        upload.setUploadID(key);
                        databaseReference.child(key).setValue(upload);



                    } else {
                        Toast.makeText(context.getApplicationContext(),
                                "upload failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }
            });

        }


    }


    @Override
    public void initComponents() {

        storageReference = firebaseStorage.
                getReference("Users/"
                        .concat(user.getPersonId())
                        .concat("/photos/")
                        .concat(generateRandomHash()));


        documentReference = firebaseFirestore
                .collection("Users")
                .document(user.getPersonId())
                .collection("Photos")
                .document();

        databaseReference = firebaseDatabase
                .getReference("Users")
                .child(user.getPersonId())
                .child("Photos");


    }

    @Override
    public void initListeners() {

    }

    private String generateRandomHash() {

        RandomData randomData = new RandomDataImpl();
        int rand = randomData.nextInt(1,12321312);

        return toString(rand).concat(".png");

    }
/*
    public void f() {

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                    Upload upload = postSnapshot.getValue(Upload.class);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(context, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
    */
}
