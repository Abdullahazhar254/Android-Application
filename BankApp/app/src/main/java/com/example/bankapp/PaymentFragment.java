package com.example.bankapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PaymentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PaymentFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PaymentFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PaymentFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PaymentFragment newInstance(String param1, String param2) {
        PaymentFragment fragment = new PaymentFragment();
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
    EditText pygetaccount,pygetamount,pyedaccount,pyedamount;
    Button btnsend;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mydb=new userDatabase(this.getContext());
        View view = inflater.inflate(R.layout.fragment_payment, container, false);

        UserActivity userActivity = (UserActivity) getActivity();
        Long userid = userActivity.getuserid();

        int getaccountno = mydb.getAccountNo(userid);
        int getamount = mydb.getAmount(userid);

        pygetaccount = view.findViewById(R.id.py_account);
        pygetamount = view.findViewById(R.id.py_amount);
        pyedaccount = view.findViewById(R.id.py_edaccount);
        pyedamount = view.findViewById(R.id.py_edamount);
        btnsend = view.findViewById(R.id.btnsend);

        pygetaccount.setText(String.valueOf(getaccountno));
        pygetamount.setText(String.valueOf(getamount));

        btnsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getaccount = pygetaccount.getText().toString();
                String getamount = pygetamount.getText().toString();
                String edaccount = pyedaccount.getText().toString();
                String edamount = pyedamount.getText().toString();

//                Toast.makeText(SignupActivity.this, "IF: "+accountno ,Toast.LENGTH_SHORT).show();

                if(edaccount.matches("") || edamount.matches("")){
                    Toast.makeText(getContext(),"Enter Data",Toast.LENGTH_LONG).show();
                }
                else if(edaccount.matches("")){
                    Toast.makeText(getContext(),"Enter Account Number",Toast.LENGTH_LONG).show();
                }
                else if(edamount.matches("")){
                    Toast.makeText(getContext(),"Enter Amount",Toast.LENGTH_LONG).show();
                }
                else{
                    if(Integer.parseInt(edamount) < Integer.parseInt(getamount)){
                        int getamountofrecuser = mydb.getAmountOfReceiveUser(edaccount);
                        if(getamountofrecuser == -1){
                            Toast.makeText(getContext(),"Transaction not available",Toast.LENGTH_LONG).show();
                        }else {
                            int updateAmountRec = getamountofrecuser+Integer.parseInt(edamount);
                            int updateCurrentUserAmount = Integer.parseInt(getamount)-Integer.parseInt(edamount);
                            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                            builder.setTitle("Transfer Money:")
                                    .setMessage("Are you sure you want to Transfer Money?")
                                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            boolean result=mydb.updateCurrentUser(updateCurrentUserAmount,userid);
                                            if(result) {
                                                boolean result2 =mydb.updateReceiverUser(updateAmountRec,Integer.parseInt(edaccount));
                                                if(result2){
                                                    boolean result3 = mydb.trans_InsertData(edamount,getaccount,edaccount);
                                                    if(result3){
                                                        Toast.makeText(getContext(),"Amount Transfer Successfully",Toast.LENGTH_LONG).show();
                                                        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                                                        transaction.replace(R.id.show_Fragments,HomeFragment.getInstance());
                                                        transaction.commit();
                                                    }else {
                                                        Toast.makeText(getContext(),"Failed 3",Toast.LENGTH_LONG).show();
                                                    }
                                                }else {
                                                    Toast.makeText(getContext(),"Failed 2",Toast.LENGTH_LONG).show();
                                                }
                                            }else {
                                                Toast.makeText(getContext(),"Failed 1",Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    }).setNegativeButton("No",null).setCancelable(false);
                            AlertDialog alert = builder.create();
                            alert.show();

                        }

                    }else {
                        Toast.makeText(getContext(),"You don't have enough amount",Toast.LENGTH_LONG).show();
                    }
                }

            }
        });

        return view;
    }
}