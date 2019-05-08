package com.enesusta.instagramclone.controller.firebase;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.enesusta.instagramclone.controller.Pointer;
import com.enesusta.instagramclone.model.User;
import com.enesusta.instagramclone.view.activities.HomeActivity;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

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
public class SignIn implements LoginDAO {

    private FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    private CollectionReference userReferences = firebaseFirestore.collection("Users");
    private Context context;


    public SignIn(Context context) {
        this.context = context;
    }


    @Override
    public void authentication() {

        User user = (User) Pointer.getObject("mainUser");

        userReferences.whereEqualTo("personEmail", user.getPersonEmail())
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {

                    String userEmail = null;
                    String userCryptedPassword = null;
                    String userFullName = null;
                    String userName = null;
                    String userUID = null;


                    boolean isCorrect;

                    for (QueryDocumentSnapshot
                            documentSnapshot : queryDocumentSnapshots) {

                        User temp = documentSnapshot.toObject(User.class);

                        userEmail = temp.getPersonEmail();
                        userCryptedPassword = temp.getPersonPassword();
                        userFullName = temp.getPersonFullName();
                        userName = temp.getPersonUserName();
                        userUID = documentSnapshot.getId();

                    }

                    isCorrect = compareUsers(user, userEmail, userCryptedPassword);


                    if (isCorrect) {

                        User userMain = new User();
                        userMain.setPersonId(userUID);
                        userMain.setPersonEmail(userEmail);
                        userMain.setPersonPassword(userCryptedPassword);
                        userMain.setPersonFullName(userFullName);
                        userMain.setPersonUserName(userName);

                        Pointer.putObject("user", userMain);
                        goMain();

                    } else {

                        Toast.makeText(context, "Email yada şifre yanlış", Toast.LENGTH_LONG).show();

                    }
                });


    }

    private boolean compareUsers(User user, String... args) {

        if (user.getPersonEmail().equals(args[0])
                && user.getPersonPassword().equals(args[1]))
            return true;
        else
            return false;

    }

    private void goMain() {

        Intent intent = new Intent(context, HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);

    }

}
