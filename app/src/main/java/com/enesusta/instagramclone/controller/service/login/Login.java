package com.enesusta.instagramclone.controller.service.login;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.enesusta.instagramclone.controller.Pointer;
import com.enesusta.instagramclone.model.User;
import com.enesusta.instagramclone.view.activities.HomeActivity;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

public class Login implements LoginService {

    private FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    private CollectionReference userReferences = firebaseFirestore.collection("Users");
    private Context context;


    public Login(Context context) {
        this.context = context;
    }


    @Override
    public void authentication() {

        User user = (User) Pointer.getObject("register");

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
