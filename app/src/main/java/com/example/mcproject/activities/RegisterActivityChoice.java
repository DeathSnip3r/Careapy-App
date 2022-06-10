package com.example.mcproject.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mcproject.R;

public class RegisterActivityChoice extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_choice);
        Intent newAcc = getIntent();
    }

    public void RegCounsellor(View v){
        // Create the intent which will start your new activity.
        Intent RegisterCouns = new Intent(RegisterActivityChoice.this, RegisterActivityCounsellor.class);
        // Start the new activity.
        startActivity(RegisterCouns);
    }

    public void RegClient(View v){
        // Create the intent which will start your new activity.
        Intent RegisterClient = new Intent(RegisterActivityChoice.this, RegisterActivityClient.class);
        // Start the new activity.
        startActivity(RegisterClient);
        finish();
    }

}



