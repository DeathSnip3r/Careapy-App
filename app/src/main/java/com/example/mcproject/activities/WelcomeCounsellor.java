package com.example.mcproject.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.mcproject.R;

public class WelcomeCounsellor extends AppCompatActivity {
    String BackupPin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_counsellor);
        Intent regAcc1 = getIntent();
        BackupPin = regAcc1.getStringExtra("Backup Pin");
    }

    private void LoadPage(View v){
        Intent RegClient = new Intent(WelcomeCounsellor.this, SignInActivity.class);
        //Start the new activity.
        startActivity(RegClient);
    }
}