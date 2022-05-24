package com.example.mcproject.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.mcproject.R;

public class SignInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
    }
    public void CreateNewAccount (View v){
        TextView CreateAcc = (TextView)findViewById(R.id.textSignInCreateNewAccount);
        CreateAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create the intent which will start your new activity.
                Intent newAcc = new Intent(SignInActivity.this, RegisterActivityChoice.class);

                // Start the new activity.
                startActivity(newAcc);
            }
        });
    }
}