package com.enesusta.instagramclone.view.recycler;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.enesusta.instagramclone.R;
import com.enesusta.instagramclone.controller.firebase.ContentManager;
import com.enesusta.instagramclone.controller.firebase.ContentService;
import com.enesusta.instagramclone.controller.firebase.Counter;
import com.enesusta.instagramclone.model.Upload;
import com.enesusta.instagramclone.view.activities.ProfileIntentActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

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

public class StreamAdapter extends RecyclerView.Adapter<StreamAdapter.StreamAdapterHolder> {

    private List<Upload> uploads;
    private Context context;

    public StreamAdapter(List<Upload> uploads, Context context) {
        super();
        this.uploads = uploads;
        this.context = context;
    }

    @NonNull
    @Override
    public StreamAdapterHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.main_stream, viewGroup, false);
        return new StreamAdapterHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull StreamAdapterHolder streamAdapterHolder, int i) {

        Upload uploadCurrent = uploads.get(i);
        streamAdapterHolder.textViewName.setText(uploadCurrent.getUserName());

        Picasso.get()
                .load(uploadCurrent.getUploadImageUrl())
                .fit()
                .centerCrop()
                .into(streamAdapterHolder.imageView);

        streamAdapterHolder.textViewName.setOnClickListener(v -> {

            Intent intent = new Intent(context, ProfileIntentActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);

        });

        streamAdapterHolder.heartCounter.setText(String.valueOf(uploadCurrent.getHeartCounter()));

        streamAdapterHolder.heartButton.setOnClickListener(v -> {

            v.setSelected(!v.isSelected());

            ContentService contentService = new Counter(uploadCurrent);
            ContentManager contentManager = new ContentManager(contentService);
            contentManager.uploadContent();

        });





       /* CommentService<String, Object> commentService = new CommentServiceImp<>(uploadCurrent);
        CommentManager manager = new CommentManager(commentService);
        manager.uploadContent();
        */
    }


    @Override
    public int getItemCount() {
        return uploads.size();
    }

    public class StreamAdapterHolder extends RecyclerView.ViewHolder {

        TextView textViewName;
        TextView heartCounter;
        TextView commentCounter;

        ImageView imageView;
        ImageButton heartButton;
        ImageButton commentButton;

        RecyclerView recyclerView;
        RecyclerView.Adapter adapter;
        RecyclerView.LayoutManager layoutManager;


        public StreamAdapterHolder(@NonNull View itemView) {

            super(itemView);
            textViewName = itemView.findViewById(R.id.main_stream_profile_name_text);
            imageView = itemView.findViewById(R.id.main_stream_content_photo);
            recyclerView = itemView.findViewById(R.id.main_stream_content_recycler_view);
            heartCounter = itemView.findViewById(R.id.main_stream_content_heart_counter);
            commentCounter = itemView.findViewById(R.id.main_stream_content_comment_counter);
            heartButton = itemView.findViewById(R.id.main_stream_content_heart);
            commentButton = itemView.findViewById(R.id.main_stream_content_comment);

        }

    }


}
