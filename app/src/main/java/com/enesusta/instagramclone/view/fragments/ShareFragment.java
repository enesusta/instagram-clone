package com.enesusta.instagramclone.view.fragments;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.enesusta.instagramclone.R;
import com.enesusta.instagramclone.controller.Initialize;
import com.enesusta.instagramclone.controller.Pointer;
import com.enesusta.instagramclone.controller.firebase.UploadImage;
import com.enesusta.instagramclone.model.Upload;
import com.enesusta.instagramclone.model.User;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import static android.app.Activity.RESULT_OK;
import static android.content.ContentValues.TAG;

public class ShareFragment extends Fragment implements Initialize {

    private static final int PICK_IMAGE_REQUEST = 1;
    private Button buttonChoseeImage;
    private Button buttonUpload;
    private TextView textViewShowUploads;
    private EditText editTextFileName;
    private ImageView imageView;
    private ProgressBar progressBar;
    private Uri imageUri;
    private FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
    private StorageReference storageReference;
    private DatabaseReference databaseReference;
    private View globalView;
    private User mUser = (User) Pointer.getObject("user");

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_share, container, false);
        globalView = view;
        init(view);


        storageReference = firebaseStorage.getReference("uploads".concat("/".concat(mUser.getPersonId())));
        databaseReference = FirebaseDatabase.getInstance().getReference("uploads");

        initListeners();

        Pointer.putObject("shareFragment",getActivity().getApplicationContext());

        return view;

    }

    private void init(View v) {

        buttonChoseeImage = v.findViewById(R.id.button_choose_image);
        buttonUpload = v.findViewById(R.id.button_upload);
        textViewShowUploads = v.findViewById(R.id.text_view_shop_uploads);
        editTextFileName = v.findViewById(R.id.edit_text_file_name);
        imageView = v.findViewById(R.id.image_view);
        progressBar = v.findViewById(R.id.prograss_bar);

    }

    private void openFileChooser() {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);

    }


    private void uploadFile() {

        if (imageUri != null) {
            storageReference.putFile(imageUri).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if (!task.isSuccessful()) {
                        throw task.getException();
                    }
                    return storageReference.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if (task.isSuccessful()) {
                        Uri downloadUri = task.getResult();
                        Log.e(TAG, "then: " + downloadUri.toString());


                        Upload upload = new Upload(editTextFileName.getText().toString().trim(),
                                downloadUri.toString());

                        Toast.makeText(getActivity().getApplicationContext(), "Upload succesful", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(getActivity().getApplicationContext(), "upload failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }


    private String getFileExtension(Uri uri) {
        ContentResolver cr = getActivity().getApplicationContext().getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(uri));
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST
                && resultCode == RESULT_OK &&
                data != null && data.getData() != null) {

            imageUri = data.getData();
            editTextFileName.setText(imageUri.toString());

            Picasso.get().load(imageUri).into(imageView);

        }
    }

    @Override
    public void initComponents() {

    }

    @Override
    public void initListeners() {

        buttonChoseeImage.setOnClickListener(v -> openFileChooser());
        buttonUpload.setOnClickListener(v -> {

            UploadImage uploadImage = new UploadImage(imageUri,getActivity().getApplicationContext());
            uploadImage.uploadContent();


        });

    }
}
