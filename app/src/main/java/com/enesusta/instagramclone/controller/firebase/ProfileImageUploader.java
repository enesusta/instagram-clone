package com.enesusta.instagramclone.controller.firebase;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.enesusta.instagramclone.controller.NullGrip;
import com.enesusta.instagramclone.controller.Pointer;
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

import java.util.HashMap;
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

@NoArgsConstructor
public class ProfileImageUploader implements ContentService {

    private FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
    private FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private StorageReference storageReference;
    private DatabaseReference databaseReference;
    private DocumentReference documentReference;
    private Uri uri;
    private Context context;
    protected User user = (User) Pointer.getObject("user");


    {

        storageReference = firebaseStorage
                .getReference("Users/"
                        .concat(user.getPersonId())
                        .concat("/photos/")
                        .concat("main.png"));

        documentReference = firebaseFirestore
                .collection("Users")
                .document(user.getPersonId())
                .collection("ProfilePhoto")
                .document();


    }

    public ProfileImageUploader(Uri uri, Context context) {
        super();
        this.uri = uri;
        this.context = context;
    }


    @Override
    public void uploadContent() {

        NullGrip<Uri> uriNullGrip = element -> (element != null ? true : false);

        if (uriNullGrip.isNull(uri))
            storageReference.putFile(uri).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if (!task.isSuccessful())
                        throw task.getException();
                    return storageReference.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {

                    if (task.isSuccessful()) {

                        Uri downloadUri = task.getResult();
                        Toast.makeText(context, "Upload successful", Toast.LENGTH_SHORT).show();

                        Map<String, Object> map = new HashMap<>();
                        map.put("pp",downloadUri);

                        documentReference.set(map);
                    } else {
                        Toast.makeText(context.getApplicationContext(),
                                "upload failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }


                }
            });
        else {
        }


    }


}
