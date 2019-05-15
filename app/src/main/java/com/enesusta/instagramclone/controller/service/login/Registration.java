package com.enesusta.instagramclone.controller.service.login;

import android.content.Context;
import android.widget.Toast;

import com.enesusta.instagramclone.controller.Pointer;
import com.enesusta.instagramclone.controller.annotations.Metadata;
import com.enesusta.instagramclone.model.User;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import es.dmoral.toasty.Toasty;
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
public class Registration implements LoginService {

    private FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    private CollectionReference userReferences = firebaseFirestore.collection("Users");
    private Context context;

    public Registration(Context context) {
        this.context = context;
    }


    @Override
    public void authentication() {

        User user = (User) Pointer.getObject("register");

        userReferences.whereEqualTo("personEmail", user.getPersonEmail())
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {

                    boolean isUser = false;

                    for (QueryDocumentSnapshot
                            documentSnapshot : queryDocumentSnapshots)
                        isUser = true;

                    addUser(isUser, user);

                });

    }

    private void addUser(boolean status, User user) {

        if (status) {
            Toasty.error(context, "Böyle bir email bulunmaktadır.", Toast.LENGTH_SHORT).show();
        } else {
            Toasty.success(context, "Kayıt başarılı!", Toast.LENGTH_LONG).show();
            userReferences.add(user);
        }
    }
}
