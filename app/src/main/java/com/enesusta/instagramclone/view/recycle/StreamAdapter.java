package com.enesusta.instagramclone.view.recycle;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.enesusta.instagramclone.R;
import com.enesusta.instagramclone.model.Upload;
import com.squareup.picasso.Picasso;

import java.util.List;

/*
 * @author : Enes Usta
 */

public class StreamAdapter extends RecyclerView.Adapter<StreamAdapter.StreamAdapterHolder> {

    private List<Upload> uploads;

    public StreamAdapter( List<Upload> uploads) {
        this.uploads = uploads;
    }

    @NonNull
    @Override
    public StreamAdapterHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v  = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.main_s , viewGroup, false);
        return new StreamAdapterHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull StreamAdapterHolder streamAdapterHolder, int i) {

        Upload uploadCurrent = uploads.get(i);
        streamAdapterHolder.textViewName.setText(uploadCurrent.getUploadName());

        Picasso.get()
                .load(uploadCurrent.getUploadImageUrl())
                .fit()
                .centerCrop()
                .into(streamAdapterHolder.imageView, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {
                    }

                    @Override
                    public void onError(Exception e) {
                    }
                });



    }


    @Override
    public int getItemCount() {
        return uploads.size();
    }

    public class StreamAdapterHolder extends RecyclerView.ViewHolder {

        TextView textViewName;
        ImageView imageView;

        public StreamAdapterHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.text_view_name);
            imageView = itemView.findViewById(R.id.image_view_upload);
        }

    }


}
