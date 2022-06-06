package com.example.mcproject.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mcproject.R;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SignInActivity extends AppCompatActivity {
    //String Logged = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
    }

    public void Login(View v) {
        String Username;
        String Password;

        EditText UsernameText = (EditText)findViewById(R.id.inputUsername);
        EditText PasswordText = (EditText)findViewById(R.id.inputPassword);
        Username= UsernameText.getText().toString();
        Password= PasswordText.getText().toString();

        if (Username.indexOf('@')>=0){
            CounsellorLogin(Username,Password);
        }
        else{
            ClientLogin(Username, Password);
        }
    }

    public void ClientLogin(String Username, String Password){

        OkHttpClient client = new OkHttpClient();

        HttpUrl.Builder urlBuilder = HttpUrl.parse("https://lamp.ms.wits.ac.za/~s2465557/client_login.php?").newBuilder();
        urlBuilder.addQueryParameter("Client_Username", Username);
        urlBuilder.addQueryParameter("Client_Password", Password);
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {

                    final String myResponse = response.body().string();
                    SignInActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (myResponse.equals("failure")){
                                Toast.makeText(getApplicationContext(), "Incorrect Username or Password", Toast.LENGTH_LONG).show();
                            }
                            else{
                                String Current_ID = myResponse.split(",")[0];
                                String User_ID= myResponse.split(",")[1];
                                Intent newAcc = new Intent(SignInActivity.this, UsersListActivity.class);
                                //startActivity(newAcc);
                                newAcc.putExtra("Current_ID", Current_ID);
                                newAcc.putExtra("User_ID", User_ID);
                                newAcc.putExtra("Type","Client");
                                // Start the new activity.
                                startActivity(newAcc);
                            }
                        }
                    });

            }
        });
    }

    public void CounsellorLogin(String Username, String Password){

        OkHttpClient client = new OkHttpClient();

        HttpUrl.Builder urlBuilder = HttpUrl.parse("https://lamp.ms.wits.ac.za/~s2465557/counsellor_login.php?").newBuilder();
        urlBuilder.addQueryParameter("Counsellor_Email", Username);
        urlBuilder.addQueryParameter("Counsellor_Password", Password);
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {

                final String myResponse = response.body().string();
                SignInActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        if (myResponse.equals("failure")){
                            Toast.makeText(getApplicationContext(), "Incorrect Username or Password", Toast.LENGTH_LONG).show();
                        }
                        else{
                            String Current_ID = myResponse.split(",")[0];
                            String User_ID= myResponse.split(",")[1];
                            Intent newAcc = new Intent(SignInActivity.this, UsersListActivity.class);
                            //startActivity(newAcc);
                            newAcc.putExtra("Current_ID", Current_ID);
                            newAcc.putExtra("User_ID", User_ID);
                            newAcc.putExtra("Type","Counsellor");
                            // Start the new activity.
                            startActivity(newAcc);
                        }
                    }
                });

            }
        });

    }

    public void CreateNewAccount (View v){
        // Create the intent which will start your new activity.
        Intent newAcc = new Intent(SignInActivity.this, RegisterActivityChoice.class);
        // Start the new activity.
        startActivity(newAcc);
    }

    public void ForgotPassword (View v){
        Intent newAcc = new Intent(SignInActivity.this, ForgotPassword.class);
        // Start the new activity.
        startActivity(newAcc);

    }
}