package com.example.bankapp;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ReceiveFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ReceiveFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ReceiveFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ReceiveFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ReceiveFragment newInstance(String param1, String param2) {
        ReceiveFragment fragment = new ReceiveFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    public static userDatabase mydb;
    ListView receivelist;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mydb=new userDatabase(this.getContext());
        View view = inflater.inflate(R.layout.fragment_receive, container, false);
        UserActivity userActivity = (UserActivity) getActivity();
        Long userid = userActivity.getuserid();
        int getaccountno = mydb.getAccountNo(userid);

        ArrayList<userTransaction> arusertrans = new ArrayList<userTransaction>();
        arusertrans.clear();
        Cursor result = mydb.trans_receive(getaccountno);
        while (result.moveToNext()){
            int amount= result.getInt(1);
            int user= result.getInt(2);
            userTransaction userta = new userTransaction(amount,user);
            arusertrans.add(userta);
        }
        receivelist = view.findViewById(R.id.receivelistview);
        ReceiverCustomAdapter receiverCustomAdapter = new ReceiverCustomAdapter(getActivity(),arusertrans);
        receivelist.setAdapter(receiverCustomAdapter);
        return view;
    }
}