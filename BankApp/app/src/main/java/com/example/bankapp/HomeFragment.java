package com.example.bankapp;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public static Fragment getInstance() {
        return new HomeFragment();
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
    ListView homelist;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mydb=new userDatabase(this.getContext());
        View view =inflater.inflate(R.layout.fragment_home, container, false);
        UserActivity userActivity = (UserActivity) getActivity();
        Long userid = userActivity.getuserid();

        ArrayList<bankUser> arbuser = new ArrayList<bankUser>();
        arbuser.clear();
        Cursor result = mydb.user_GetData(userid);
        while (result.moveToNext()){
            String name = result.getString(1);
            int account= result.getInt(5);
            int amount= result.getInt(6);
            bankUser bkuser = new bankUser(name,account,amount);
            arbuser.add(bkuser);
        }
        homelist=view.findViewById(R.id.homelistview);
        HomeCustomAdapter homeCustomAdapter = new HomeCustomAdapter(getActivity(),arbuser);
        homelist.setAdapter(homeCustomAdapter);
        return view;
    }
}