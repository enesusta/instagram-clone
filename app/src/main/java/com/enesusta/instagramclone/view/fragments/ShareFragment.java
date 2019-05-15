package com.enesusta.instagramclone.view.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.enesusta.instagramclone.R;
import com.enesusta.instagramclone.controller.Initialize;
import com.enesusta.instagramclone.controller.Tool;
import com.enesusta.instagramclone.controller.annotations.Metadata;
import com.enesusta.instagramclone.controller.enums.Priority;
import com.enesusta.instagramclone.controller.enums.Type;
import com.enesusta.instagramclone.controller.service.content.Content;
import com.enesusta.instagramclone.controller.service.content.ContentService;
import com.enesusta.instagramclone.controller.service.content.ImageContent;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

import static android.app.Activity.RESULT_OK;

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

@Metadata(
        priority = Priority.MEDIUM,
        type = Type.VIEW,
        author = "Enes Usta",
        lastModified = "27/04/2019"
)


public class ShareFragment extends Fragment implements Initialize, Tool {

    private static final int PICK_IMAGE_REQUEST = 1;
    private Button buttonChoseeImage;
    private Button buttonUpload;
    private ImageView imageView;
    private EditText editText;
    private Uri imageUri;
    private Map<String,Object> map;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_share, container, false);

        init(view);
        initListeners();

        return view;

    }

    private void init(View v) {

        map = new HashMap<>();
        buttonChoseeImage = v.findViewById(R.id.button_choose_image);
        buttonUpload = v.findViewById(R.id.button_upload);
        imageView = v.findViewById(R.id.image_view);
        editText = v.findViewById(R.id.edit_text_file_name);

    }

    private void openFileChooser() {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST
                && resultCode == RESULT_OK &&
                data != null && data.getData() != null) {

            imageUri = data.getData();
            Picasso.get().load(imageUri).into(imageView);

            map.put("uri",imageUri);
            map.put("text",toChar(editText));

        }
    }

    @Override
    public void initComponents() {

    }


    @Override
    public void initListeners() {

        buttonChoseeImage.setOnClickListener(v -> openFileChooser());

        buttonUpload.setOnClickListener(v -> {

            ContentService contentService =
                    new ImageContent(map,getActivity().getApplicationContext());
            Content content = new Content(contentService);
            content.upload();

        });

    }
}
