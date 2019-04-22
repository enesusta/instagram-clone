package com.enesusta.instagramclone.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.enesusta.instagramclone.R;

import java.util.List;

public class adapterModel extends BaseAdapter {

    List<UserModel> list;
    Context context;
    Activity activity;

    public adapterModel(List<UserModel> list, Context context, Activity activity) {
        this.list = list;
        this.context = context;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = LayoutInflater.from(context).inflate(R.layout.ders3_layout, parent, false);
        TextView isim = convertView.findViewById(R.id.isim);
        TextView soyisim = convertView.findViewById(R.id.soyisim);
        TextView telNo = convertView.findViewById(R.id.telNo);

        LinearLayout linearLayout = convertView.findViewById(R.id.anaLayout);

        final String isimS = list.get(position).getName();
        final String soyisimS = list.get(position).getSurname();
        final String telNoS = new Integer(list.get(position).getAge()).toString();


        isim.setText(isimS);
        soyisim.setText(soyisimS);
        telNo.setText(telNoS);


        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, ActivityDers4.class);
                intent.putExtra("isim", isimS);
                intent.putExtra("soyisim", soyisimS);
                intent.putExtra("tel", telNoS);

                activity.startActivity(intent);

            }
        });


        return convertView;


    }
}
