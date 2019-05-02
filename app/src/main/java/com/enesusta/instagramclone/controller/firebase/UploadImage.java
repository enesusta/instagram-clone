package com.enesusta.instagramclone.controller.firebase;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.enesusta.instagramclone.controller.Initialize;
import com.enesusta.instagramclone.controller.NullGrip;
import com.enesusta.instagramclone.controller.components.Tool;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;


import java.util.Random;

import static android.content.ContentValues.TAG;

public class UploadImage extends Share implements Content, Initialize, Tool {

    private FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
    private StorageReference storageReference;
    private Uri uri;
    private Context context;


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
                        Log.e(TAG, "then: " + downloadUri.toString());

                        Toast.makeText(context,
                                "Upload succesful", Toast.LENGTH_SHORT).show();

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
                        .concat(super.user.getPersonId())
                        .concat("/share/")
                        .concat(generateRandomHash()));


    }

    @Override
    public void initListeners() {

    }

    private String generateRandomHash() {

        Random rand = new Random(150);
        Integer randomInt = rand.nextInt();
        return toString(randomInt.hashCode()).concat(".png");

    }

}
