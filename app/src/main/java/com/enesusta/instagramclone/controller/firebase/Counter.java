package com.enesusta.instagramclone.controller.firebase;

import com.enesusta.instagramclone.model.Upload;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Counter implements ContentService {

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference;
    private Upload upload;

    public Counter(Upload upload) {
        this.upload = upload;
    }

    void initReference() {

        databaseReference =
                firebaseDatabase
                        .getReference("Users")
                        .child(upload.getUserID())
                        .child("Content")
                        .child(upload.getUploadID());


    }

    @Override
    public void uploadContent() {

        int currentCounter = upload.getHeartCounter();
        upload.setHeartCounter(currentCounter++);
        databaseReference.setValue(upload);

    }


}
