package com.example.mcproject.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.mcproject.R;

public class UsersListActivity extends AppCompatActivity {
    String Current_ID;
    String User_ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_list);
        Intent users = getIntent();

        Current_ID = users.getStringExtra("Current_ID");
        User_ID = users.getStringExtra("User_ID ");
    }
}