package com.example.bankapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ProfileCustomAdapter extends ArrayAdapter {
    Activity context;
    ArrayList<bankUser> arrayList;

    public ProfileCustomAdapter(@NonNull Activity context, ArrayList<bankUser> arrayList) {
        super(context, R.layout.activity_profile);
        this.context=context;
        this.arrayList=arrayList;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.activity_profile,null,true);
        bankUser b_user= arrayList.get(position);

        final TextView name= rowView.findViewById(R.id.p_name);
        final TextView email= rowView.findViewById(R.id.p_email);
        final TextView contact= rowView.findViewById(R.id.p_contact);
        final TextView account= rowView.findViewById(R.id.p_account);
        final TextView amount= rowView.findViewById(R.id.p_amount);

        name.setText(b_user.getName());
        email.setText(b_user.getEmail());
        contact.setText(b_user.getContact());
        account.setText(String.valueOf(b_user.getAccount()));
        amount.setText(String.valueOf(b_user.getAmount()));

        return rowView;
    }
    @Override
    public int getCount() {
        return arrayList.size();
    }
}
