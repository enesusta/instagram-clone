package com.enesusta.instagramclone.controller.firebase;

/*
 * @author : Enes Usta
 */

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public abstract class Share<T> {

    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

//    DocumentReference documentReference = firebaseFirestore.collection("Users").document(documentSnapshot.getId()).collection("Notes").document();

    public abstract int getResource();




}
