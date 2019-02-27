package com.enesusta.shakosuru;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.ident> {

    Context context;
    List<UserPhotoModel> list;

    public PhotoAdapter(Context context, List<UserPhotoModel> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public ident onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.recylce_temel, viewGroup, false);
        return new ident(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ident ident, int i) {

         String isim = list.get(i).getName() + " " + list.get(i).getSurname();
       ident.kisi.setText(isim);
        ident.yas.setText(String.valueOf(list.get(i).getAge()));
      ident.img.setImageResource(list.get(i).getResimId());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ident extends RecyclerView.ViewHolder {

        ImageView img;
        TextView kisi;
        TextView yas;

        public ident(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.resim);
            kisi = itemView.findViewById(R.id.kisi);
            yas = itemView.findViewById(R.id.yas);

        }

    }


}
