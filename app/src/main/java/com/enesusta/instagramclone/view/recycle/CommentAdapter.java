package com.enesusta.instagramclone.view.recycle;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.enesusta.instagramclone.R;
import com.enesusta.instagramclone.model.Comment;
import com.enesusta.instagramclone.model.Upload;
import com.enesusta.instagramclone.view.activities.CommentActivity;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

/*
 * @author : Enes Usta
 */

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentAdapterHolder> {

    private List<Comment> commentList;
    private Context context;

    public CommentAdapter(List<Comment> commentList, Context context) {
        this.commentList = commentList;
        this.context = context;
    }

    @NonNull
    @Override
    public CommentAdapterHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.comment_stream, viewGroup, false);
        return new CommentAdapterHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentAdapterHolder holder, int i) {

        Comment comment = commentList.get(i);
        holder.userName.setText(comment.getUserName());
        holder.content.setText(comment.getContentOfComment());

        holder.relativeLayout.setOnClickListener( act -> {

            Intent commentIntent = new Intent(context, CommentActivity.class);

        });

    }


    @Override
    public int getItemCount() {
        return commentList.size();
    }

    public class CommentAdapterHolder extends RecyclerView.ViewHolder {

        TextView userName;
        TextView content;
        ImageView profilePhoto;
        RelativeLayout relativeLayout;

        public CommentAdapterHolder(@NonNull View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.comment_user_name);
            profilePhoto = itemView.findViewById(R.id.comment_profile_photo);
            content = itemView.findViewById(R.id.comment_text);
            relativeLayout = itemView.findViewById(R.id.relativeLayoutTwo);
        }

    }


}
