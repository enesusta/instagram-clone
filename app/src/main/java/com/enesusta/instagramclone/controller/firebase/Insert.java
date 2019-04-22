package com.enesusta.instagramclone.controller.firebase;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.enesusta.instagramclone.R;
import com.enesusta.instagramclone.controller.LoginException;
import com.enesusta.instagramclone.controller.MyToast;
import com.enesusta.instagramclone.model.User;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.muddzdev.styleabletoast.StyleableToast;

import lombok.Getter;
import lombok.Setter;


/*
 * @author : Enes Usta
 */

public class Insert implements InsertDAO {

    private FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    private CollectionReference collectionReference = firebaseFirestore.collection("Users");
    private Context context;
    private MyToast myToast;
    @Getter
    private boolean statusBool;

    public Insert() {

    }

    public Insert(Context context) {
        this.context = context;
        myToast = new MyToast(context);
    }


    @Override
    public void execute(User user) {

        collectionReference.whereEqualTo("personEmail", user.getPersonEmail())
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        boolean isUser = false;
                        String email = null;
                        String pass = null;

                        for (QueryDocumentSnapshot
                                documentSnapshot : queryDocumentSnapshots) {

                            isUser = true;
                            User x = documentSnapshot.toObject(User.class);

                            email = x.getPersonEmail();
                            pass = x.getPersonPassword();

                        }

                        // boolean isCorrect = compareUsers(user, email, pass);

                        //  loginMessage(isCorrect);

                        statusBool = compareUsers(user, email, pass);


                    }
                });

    }

    private void statusMessage(boolean status, User user) {

        if (status) {
            StyleableToast.makeText(context, "Bu kullanıcı ismi kullanılmaktadır.", R.style.exampleToast);
        } else {
            myToast.show("Kayıt başarılı!");
            collectionReference.add(user);
        }
    }

    private boolean compareUsers(User user, String... args) {

        if (user.getPersonEmail().equals(args[0])
                && user.getPersonPassword().equals(args[1]))
            return true;
        else
            return false;

    }

    private void loginMessage(boolean bool) {

        if (bool) {
        } else {
            myToast.show("Hatalı kullanıcı adı veya şifre.");
        }
    }


}
