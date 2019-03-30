package com.enesusta.shakosuru.activities;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.enesusta.shakosuru.R;

import org.w3c.dom.Text;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    public List<User> list;
    public Context context;

    public UserAdapter(List<User> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView isim,yas,numara,takim;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            isim = itemView.findViewById(R.id.isimText);
            yas = itemView.findViewById(R.id.yasText);
            numara = itemView.findViewById(R.id.numaraText);
            takim = itemView.findViewById(R.id.takimText);
        }

    }


    @NonNull
    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.activity_ders7_recycle,viewGroup,false);
        return new UserAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.ViewHolder viewHolder, int i) {

        viewHolder.isim.setText(list.get(i).isim.toString());
        viewHolder.numara.setText(list.get(i).numara.toString());;
        viewHolder.takim.setText(list.get(i).takim.toString());
        viewHolder.yas.setText(list.get(i).yas);


    }

    @Override
    public int getItemCount() {
        return list.size();
    }


}
