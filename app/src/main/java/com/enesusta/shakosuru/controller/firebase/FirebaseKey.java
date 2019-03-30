package com.enesusta.shakosuru.controller.firebase;

import com.google.firebase.database.DatabaseReference;

public interface FirebaseKey {
    default String getKey(DatabaseReference reference) {
        return reference.getKey();
    }
}
