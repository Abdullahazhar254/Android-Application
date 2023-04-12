package com.example.bankapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

public class UserActivity extends AppCompatActivity {

    private long userid;
    TabLayout bottomNavigationView;
    Button btnsignout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        userid=getIntent().getLongExtra("userid",0);

        bottomNavigationView=findViewById(R.id.bottom_navigation);
        setDefaultFragment();
        bottomNavigationView.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                FragmentTransaction frt = getSupportFragmentManager().beginTransaction();
                switch (tab.getPosition())
                {
                    case 0:
                        HomeFragment hfrg = new HomeFragment();
                        frt.replace(R.id.show_Fragments,hfrg);
                        frt.commit();
                        break;
                    case 1:
                        ProfileFragment Pfrg = new ProfileFragment();
                        frt.replace(R.id.show_Fragments,Pfrg);
                        frt.commit();
                        break;
                    case 2:
                        PaymentFragment pyfrg = new PaymentFragment();
                        frt.replace(R.id.show_Fragments,pyfrg);
                        frt.commit();
                        break;
                    case 3:
                        HistoryFragment hifrg = new HistoryFragment();
                        frt.replace(R.id.show_Fragments,hifrg);
                        frt.commit();
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        btnsignout = findViewById(R.id.btnsignout);
        btnsignout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
    }
    private void setDefaultFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.show_Fragments,HomeFragment.getInstance());
        transaction.commit();
    }

    public Long getuserid(){
        return userid;
    }
}