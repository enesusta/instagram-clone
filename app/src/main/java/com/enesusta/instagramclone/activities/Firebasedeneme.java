package com.enesusta.instagramclone.activities;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.enesusta.instagramclone.R;
import com.enesusta.instagramclone.controller.MyToast;
import com.enesusta.instagramclone.controller.crypt.Crpyt;
import com.enesusta.instagramclone.controller.firebase.Insert;
import com.enesusta.instagramclone.model.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;

public class Firebasedeneme extends AppCompatActivity {

    private static final String TAG = "Firebasedeneme";
    private static final String KEY_TITLE = "title";
    private static final String KEY_DESCRIPTION = "description";

    private EditText editTextTitle;
    private EditText editTextDescription;
    private TextView textViewData;
    private EditText editTextPriority;
    private EditText loadUser;
    private Button saveButton;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private DocumentReference noteRef = db.document("Notebook/My First Note");
    private CollectionReference noteBookRef = db.collection("Notebook");
    private ListenerRegistration listenerRegistration;
    private CollectionReference usersRef = db.collection("Users");


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebasedeneme);
        editTextTitle = findViewById(R.id.edit_text_title);
        editTextDescription = findViewById(R.id.edit_text_description);
        textViewData = findViewById(R.id.text_view_data);
        editTextPriority = findViewById(R.id.edit_text_priority);
        loadUser = findViewById(R.id.load_user);
        saveButton = findViewById(R.id.save_button);
        saveUser();

    }

    public void saveNote(View v) {

        String title = editTextTitle.getText().toString();
        String description = editTextDescription.getText().toString();
/*
        Map<String, Object> note = new HashMap<>();
        note.put(KEY_TITLE, title);
        note.put(KEY_DESCRIPTION, description);
*/
        MyToast myToast = new MyToast(getApplicationContext());
        //Note note = new Note(title, description);


        noteRef.set(new Note())
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        myToast.show("Note Saved");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        myToast.show("Error!");
                    }
                });
    }

    public void saveUser() {

        Toast.makeText(getApplicationContext(), "Girdi", Toast.LENGTH_LONG);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String title = editTextTitle.getText().toString();
                String description = editTextDescription.getText().toString();
                Crpyt crpyt = new Crpyt(description);


                User user = User.getInstance();
                user.setPersonEmail(title);
                user.setPersonPassword(crpyt.getPass());

                Insert insert = new Insert(getApplicationContext());
                insert.execute(user);

                Log.d(TAG, "Calisiyor");

            }
        });


    }


    public void addNote(View v) {

        String title = editTextTitle.getText().toString();
        String description = editTextDescription.getText().toString();

        MyToast myToast = new MyToast(getApplicationContext());

        if (editTextPriority.length() == 0) {
            editTextPriority.setText("0");
        }

        int priority = Integer.parseInt(editTextPriority.getText().toString());

        Note note = new Note(title, description, priority);


        noteBookRef.add(note)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {

                    }
                });


    }

    public void loadNotes(View v) {

        String title = editTextTitle.getText().toString();
        String description = editTextDescription.getText().toString();
        MyToast myToast = new MyToast(getApplicationContext());


        noteBookRef.whereEqualTo("priority", 3)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        String data = "";

                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {

                            Note note = documentSnapshot.toObject(Note.class);
                            note.setDocumentID(documentSnapshot.getId());

                            String documentId = note.getDocumentID();
                            String title = note.getTitle();
                            String description = note.getDescription();
                            int priority = note.getPriority();

                            data += "Id: " + documentId + "\nTitle: " + title + "\nDescription :" + description + "\npriority : " + priority + "\n\n";

                        }
                        textViewData.setText(data);
                    }
                });

    }

    public void loadUser(View v) {

        String title = editTextTitle.getText().toString();
        String description = editTextDescription.getText().toString();
        MyToast myToast = new MyToast(getApplicationContext());

        usersRef.whereEqualTo("personEmail", title)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        String data = "";


                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {

                            User user = documentSnapshot.toObject(User.class);

                            String personName = user.getPersonEmail();
                            String pass = user.getPersonPassword();

                            data += "Name is :" + personName + " \n pass is : " + pass;

                            DocumentReference documentReference = db.collection("Users").document(documentSnapshot.getId()).collection("Notes").document();

                            Note note1 = new Note(title, description, 2);

                            documentReference.set(note1)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            myToast.show("Not " + personName + " adli kullaniciya eklendi!");
                                        }
                                    });

                        }

                        loadUser.setText(data);

                    }
                });

    }

    @Override
    protected void onStart() {

        MyToast myToast = new MyToast(getApplicationContext());

        super.onStart();

        noteBookRef.addSnapshotListener(this, new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {

                if (e != null) {
                    return;
                }

                String data = "";

                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {

                    Note note = documentSnapshot.toObject(Note.class);
                    note.setDocumentID(documentSnapshot.getId());

                    String documentId = note.getDocumentID();
                    String title = note.getTitle();
                    String description = note.getDescription();
                    int priority = note.getPriority();

                    data += "Id: " + documentId + "\nTitle: " + title + "\nDescription :" + description + "\npriority : " + priority + "\n\n";
                }

                textViewData.setText(data);


            }
        });








     /*
        listenerRegistration = noteRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {

                if (e != null) {
                    myToast.show("Error while loading!");
                    Log.d(TAG, e.toString());
                    return;
                }

                if (documentSnapshot.exists()) {
                    /*
                    String title = documentSnapshot.getString(KEY_TITLE);
                    String description = documentSnapshot.getString(KEY_DESCRIPTION);
                    //Map<String, Object> note = documentSnapshot.getData();


                    Note note = documentSnapshot.toObject(Note.class);

                    String title = note.getTitle();
                    String description = note.getDescription();


                    textViewData.setText("Title : " + title + "\n" + "Description :" + description);
                } else {
                    myToast.show("Document doesnt exist");
                }
            }

        });

            */

    }

    @Override
    protected void onStop() {
        super.onStop();
        listenerRegistration.remove();
    }

    public void loadNote(View v) {

        MyToast myToast = new MyToast(getApplicationContext());

        noteRef.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {

                            Note note = documentSnapshot.toObject(Note.class);

                            String title = note.getTitle();
                            String description = note.getDescription();

                            /*
                            String title = documentSnapshot.getString(KEY_TITLE);
                            String description = documentSnapshot.getString(KEY_DESCRIPTION);

                            //Map<String, Object> note = documentSnapshot.getData();
*/
                            textViewData.setText("Title : " + title + "\n" + "Description :" + description);

                        } else {
                            myToast.show("Document doesnt exist");
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        myToast.show("An Error Occured");
                    }
                });
    }

    public void updateDescription(View v) {

       /* String description = editTextDescription.getText().toString();

        Map<String, Object> note = new HashMap<>();
        note.put(KEY_DESCRIPTION, description);

        //noteRef.set(note, SetOptions.merge());
        noteRef.update(KEY_DESCRIPTION, description);

*/

    }


}
