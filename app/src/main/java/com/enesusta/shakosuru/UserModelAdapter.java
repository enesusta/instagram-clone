package com.enesusta.shakosuru;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class UserModelAdapter extends BaseAdapter {

    List<UserModel> list;
    Context context;

    public UserModelAdapter(List<UserModel> list, Context context) {

        this.list = list;
        this.context = context;

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

        View layout
        = LayoutInflater.
                from(context).
                inflate(R.layout.temel_tasarim
                        ,parent,false);
        TextView name = layout.findViewById(R.id.name);
        TextView surname = layout.findViewById(R.id.surname);
        TextView yas = layout.findViewById(R.id.age);

        name.setText(list.get(position).getName());
        surname.setText(list.get(position).getSurname());
        yas.setText(new Integer(list.get(position).getAge()).toString());

        return layout;

    }
}
