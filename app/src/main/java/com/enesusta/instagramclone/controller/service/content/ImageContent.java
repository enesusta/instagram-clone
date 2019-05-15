package com.enesusta.instagramclone.controller.service.content;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.enesusta.instagramclone.controller.NullGrip;
import com.enesusta.instagramclone.controller.Pointer;
import com.enesusta.instagramclone.controller.annotations.Metadata;
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

import java.util.Map;

import lombok.NoArgsConstructor;

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


@Metadata(author = "Enes Usta")
@NoArgsConstructor
public class ImageContent implements ContentService {

    private FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private StorageReference storageReference;
    private DatabaseReference databaseReference;
    private Uri uri;
    private Context context;
    protected User user = (User) Pointer.getObject("user");
    private Map<String, Object> map;


    public ImageContent(Map<String, Object> map, Context context) {
        this.map = map;
        this.context = context;
        init();
    }


    private void init() {

        storageReference = firebaseStorage.
                getReference("Users/"
                        .concat(user.getPersonId())
                        .concat("/photos/")
                        .concat(generateRandomHash()));


        databaseReference = firebaseDatabase
                .getReference("Users")
                .child(user.getPersonId())
                .child("Content");

    }


    private String generateRandomHash() {

        RandomData randomData = new RandomDataImpl();
        int rand = randomData.nextInt(1, 12321312);

        return String.valueOf(rand).concat(".png");

    }

    @Override
    public void upload() {

        NullGrip<Uri> uriCheck = element -> {
            return element != null ? true : false;
        };

        Uri uri = (Uri) map.get("uri");

        if (uriCheck.isNull(uri)) {

            storageReference.putFile(uri).continueWithTask(task -> {

                if (!task.isSuccessful())
                    throw task.getException();

                return storageReference.getDownloadUrl();

            }).addOnCompleteListener(task -> {

                if (task.isSuccessful()) {
                    Uri downloadUri = task.getResult();

                    Toast.makeText(context,
                            "Upload succesful", Toast.LENGTH_SHORT).show();

                    Upload upload = new Upload("", downloadUri.toString());
                    upload.setUserName(user.getPersonUserName());
                    upload.setUserID(user.getPersonId());
                    upload.setHeartCounter(0);

                    String key = databaseReference.push().getKey();

                    upload.setUploadID(key);
                    upload.setUploadText(map.get("text").toString());

                    databaseReference.child(key).setValue(upload);

                } else {
                    Toast.makeText(context.getApplicationContext(),
                            "upload failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }

            });

        }

    }
}
