package com.example.mcproject.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.motion.widget.MotionLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.mcproject.R;

public class startupAnimatedSplash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup_animated_splash);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent newAcc = new Intent(startupAnimatedSplash.this, SignInActivity.class);
                startActivity(newAcc);
                finish();
            }
        }, 3000);
    }
}