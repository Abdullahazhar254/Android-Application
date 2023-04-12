package com.example.bankapp;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class HomeCustomAdapter extends ArrayAdapter {
    Activity context;
    ArrayList<bankUser> arrayList;

    public HomeCustomAdapter(@NonNull Activity context, ArrayList<bankUser> arrayList) {
        super(context, R.layout.activity_home);
        this.context=context;
        this.arrayList=arrayList;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.activity_home,null,true);
        bankUser b_user= arrayList.get(position);

        final TextView name= rowView.findViewById(R.id.hc_name);
        final TextView account= rowView.findViewById(R.id.hc_account);
        final TextView amount= rowView.findViewById(R.id.hc_amount);

        name.setText(b_user.getName());
        account.setText(String.valueOf(b_user.getAccount()));
        amount.setText(String.valueOf(b_user.getAmount()));

        return rowView;
    }
    @Override
    public int getCount() {
        return arrayList.size();
    }
}
