package com.example.mystic_merchants_domain;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class UserAdapter extends BaseAdapter {

    private Context context;
    private List<Users> users;

    public UserAdapter(Context context, List<Users> users) {
        this.context = context;
        this.users = users;
    }

    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public Object getItem(int position) {
        return users.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.listviewusers, parent, false);
        }

        //Retrieve user
        Users user = users.get(position);

        //Find the views in listviewusers.xml
        TextView textView = convertView.findViewById(R.id.user_TextView);

        //Set user details (username)
        textView.setText(user.getUsername());

        return convertView;
    }
}


