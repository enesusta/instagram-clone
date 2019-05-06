package com.enesusta.instagramclone.view.recycle;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.enesusta.instagramclone.R;
import com.enesusta.instagramclone.model.Upload;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

/*
 * @author : Enes Usta
 */

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentAdapterHolder> {

    private List<Upload> uploads;

    public CommentAdapter(List<Upload> uploads) {
        this.uploads = uploads;
    }

    @NonNull
    @Override
    public CommentAdapterHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v  = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.main_s , viewGroup, false);
        return new CommentAdapterHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentAdapterHolder streamAdapterHolder, int i) {

        Upload uploadCurrent = uploads.get(i);
/*        streamAdapterHolder.textViewName.setText(uploadCurrent.getUploadName());

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
                */



    }


    @Override
    public int getItemCount() {
        return uploads.size();
    }

    public class CommentAdapterHolder extends RecyclerView.ViewHolder {

        TextView userName;
        TextView content;
        ImageView profilePhoto;

        public CommentAdapterHolder(@NonNull View itemView) {
            super(itemView);
            userName= itemView.findViewById(R.id.comment_user_name);
            profilePhoto = itemView.findViewById(R.id.comment_profile_photo);
            content = itemView.findViewById(R.id.comment_text);
        }

    }


}
