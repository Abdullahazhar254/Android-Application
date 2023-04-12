package com.example.bankapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {

    public static userDatabase mydb;

    Button btnsignup;
    EditText siName, siPass, siEmail, siPhone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mydb=new userDatabase(this);

        siName=findViewById(R.id.edsiName);
        siEmail=findViewById(R.id.edsiEmail);
        siPass=findViewById(R.id.edsiPass);
        siPhone=findViewById(R.id.edsiPhone);

        btnsignup=findViewById(R.id.btnsignup1);
        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Name = siName.getText().toString();
                String Email = siEmail.getText().toString();
                String Pass = siPass.getText().toString();
                String Phone = siPhone.getText().toString();

//                Toast.makeText(SignupActivity.this, "IF: "+accountno ,Toast.LENGTH_SHORT).show();

                if(Name.matches("") || Email.matches("") || Pass.matches("") || Phone.matches("")){
                    Toast.makeText(SignupActivity.this,"Enter Data",Toast.LENGTH_LONG).show();
                }
                else if(Name.matches("")){
                    Toast.makeText(SignupActivity.this,"Enter Name",Toast.LENGTH_LONG).show();
                }
                else if(Email.matches("")){
                    Toast.makeText(SignupActivity.this,"Enter Email",Toast.LENGTH_LONG).show();
                }
                else if(Pass.matches("")){
                    Toast.makeText(SignupActivity.this,"Enter Password",Toast.LENGTH_LONG).show();
                }
                else if(Phone.matches("")){
                    Toast.makeText(SignupActivity.this,"Enter Phone",Toast.LENGTH_LONG).show();
                }
                else{
                    int userCount = 0;
                    int acc = 0;
                    Cursor result = mydb.user_count();
                    if(result.getCount() != 0){
                        result.moveToNext();
                        userCount = result.getInt(0);
                        acc = (userCount + 1) + 1100;
                    }
                    int accountno = acc;
                    int balance = 50000;
                    AlertDialog.Builder builder = new AlertDialog.Builder(SignupActivity.this);
                    builder.setTitle("Bank Detail:")
                            .setMessage("Your Bank Account Number: "+accountno)
                            .setMessage("Your Bank Balance: "+balance)
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    boolean result=mydb.user_InsertData(Name,Email,Pass,Phone,accountno,balance);
                                    if(result) {
                                        Toast.makeText(SignupActivity.this,"Sign up sucessfully ",Toast.LENGTH_LONG).show();
                                        Intent in = new Intent(SignupActivity.this,LoginActivity.class);
                                        startActivity(in);
                                    }else {
                                        Toast.makeText(SignupActivity.this,"Failed",Toast.LENGTH_LONG).show();
                                    }

                                }
                            }).setCancelable(false);
                    AlertDialog alert = builder.create();
                    alert.show();
                }
            }
        });
    }
}