package com.example.mcproject.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mcproject.R;

public class clientProblems extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_problems);
        Intent regAcc = getIntent();
    }
    public void FinishRegClient(View v){
        Button problem= (Button) findViewById(R.id.btnRegClientProblems);
        problem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create the intent which will start your new activity.
                Intent problemClient = new Intent(clientProblems.this, ChatActivity.class);
                // Start the new activity.
                startActivity(problemClient);
            }
        });
    }
}