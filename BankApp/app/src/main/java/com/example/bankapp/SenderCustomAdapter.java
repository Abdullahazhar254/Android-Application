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

public class SenderCustomAdapter extends ArrayAdapter {
    Activity context;
    ArrayList<userTransaction> arrayList;

    public SenderCustomAdapter(@NonNull Activity context, ArrayList<userTransaction> arrayList) {
        super(context, R.layout.activity_sender);
        this.context=context;
        this.arrayList=arrayList;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.activity_sender,null,true);
        userTransaction usertrans= arrayList.get(position);

        final TextView amount= rowView.findViewById(R.id.sd_amount);
        final TextView account= rowView.findViewById(R.id.sd_user);

        amount.setText(String.valueOf(usertrans.getAmount()));
        account.setText(String.valueOf(usertrans.getReceiver()));

        return rowView;
    }
    @Override
    public int getCount() {
        return arrayList.size();
    }
}
