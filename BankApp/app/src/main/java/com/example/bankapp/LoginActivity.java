package com.example.bankapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    public static userDatabase mydb;

    Button btnlogin, btnsignup;
    EditText lgPass, lgEmail;

    private long userid=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mydb=new userDatabase(this);

        lgEmail=findViewById(R.id.edlgEmail);
        lgPass=findViewById(R.id.edlgPass);

        btnlogin=findViewById(R.id.btnlogin);
        btnsignup=findViewById(R.id.btnsignup);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Email = lgEmail.getText().toString();
                String Pass = lgPass.getText().toString();
                boolean show = mydb.user_Signin(Email, Pass);
                if(Email.matches("") || Pass.matches("")){
                    Toast.makeText(LoginActivity.this,"Enter Data",Toast.LENGTH_LONG).show();
                }
                else if(Email.matches("")){
                    Toast.makeText(LoginActivity.this,"Enter Email",Toast.LENGTH_LONG).show();
                }
                else if(Pass.matches("")){
                    Toast.makeText(LoginActivity.this,"Enter Password",Toast.LENGTH_LONG).show();
                }
                else if (show) {
                    Toast.makeText(LoginActivity.this, "Login Successfully", Toast.LENGTH_LONG).show();
                    userid = mydb.getUserId(Email);
                    Intent in = new Intent(LoginActivity.this,UserActivity.class);
                    in.putExtra("userid",userid);
                    startActivity(in);
                }
                else {
                    Toast.makeText(LoginActivity.this, "Not Login", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(LoginActivity.this,SignupActivity.class);
                startActivity(in);
            }
        });
    }
}