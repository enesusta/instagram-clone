package com.enesusta.instagramclone.controller.firebase;

import android.content.Context;
import android.widget.Toast;

import com.enesusta.instagramclone.controller.Pointer;
import com.enesusta.instagramclone.controller.Tool;
import com.enesusta.instagramclone.model.User;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import es.dmoral.toasty.Toasty;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class SignUp implements LoginDAO, Tool {

    private FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    private CollectionReference userReferences = firebaseFirestore.collection("Users");
    private Context context;

    public SignUp(Context context) {
        this.context = context;
    }


    @Override
    public void authentication() {

        User user = (User) Pointer.getObject("mainUser");

        userReferences.whereEqualTo("personEmail", user.getPersonEmail())
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        String userEmail = null;
                        String userCryptedPassword = null;
                        boolean isUser = false;

                        for (QueryDocumentSnapshot
                                documentSnapshot : queryDocumentSnapshots)
                            isUser = true;

                        addUser(isUser, user);

                    }
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
