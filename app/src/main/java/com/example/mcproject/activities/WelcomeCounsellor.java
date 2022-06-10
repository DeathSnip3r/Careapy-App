package com.example.mcproject.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.mcproject.R;

public class WelcomeCounsellor extends AppCompatActivity {
    String BackupPin;
    String Name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_counsellor);
        Intent regAcc1 = getIntent();
        BackupPin = regAcc1.getStringExtra("Backup Pin");
        Name = regAcc1.getStringExtra("Name");

        TextView pin = findViewById(R.id.backUpPin);
        pin.setText(BackupPin);
        TextView name = findViewById(R.id.counsellorName);
        name.setText(Name);

    }

    public void LoadPage(View v){
        Intent RegClient = new Intent(WelcomeCounsellor.this, SignInActivity.class);
        //Start the new activity.
        startActivity(RegClient);
        finish();
    }
}