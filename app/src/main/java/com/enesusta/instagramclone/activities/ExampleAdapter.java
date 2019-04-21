package com.enesusta.instagramclone.activities;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.enesusta.instagramclone.R;

import java.util.List;

public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder> {

    private List<ExampleItem> exampleItems;


    public ExampleAdapter(List<ExampleItem> exampleItems) {
        this.exampleItems = exampleItems;

    }


    public static class ExampleViewHolder extends RecyclerView.ViewHolder {

        public ImageView mImageView;
        public TextView mTextView1;
        public TextView mTextView2;

        public ExampleViewHolder(@NonNull View itemView) {
            super(itemView);

            mImageView = itemView.findViewById(R.id.imageView);
            mTextView1 = itemView.findViewById(R.id.textView);
            mTextView2 = itemView.findViewById(R.id.textView2);

        }
    }

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.main_stream, viewGroup, false);
        ExampleViewHolder evh = new ExampleViewHolder(v);
        return evh;

    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder exampleViewHolder, int i) {

        ExampleItem currentItem = exampleItems.get(i);
        exampleViewHolder.mImageView.setImageResource(currentItem.getmImageResource());
        exampleViewHolder.mTextView1.setText(currentItem.getmText1());
        exampleViewHolder.mTextView2.setText(currentItem.getmText2());

    }

    @Override
    public int getItemCount() {
        return exampleItems.size();
    }


}
