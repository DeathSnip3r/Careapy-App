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
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SignInActivity extends AppCompatActivity {
    String Logged;
    private TextView testTextview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
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
                if (response.isSuccessful()){
                    String myResponse = response.body().string();
                    SignInActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            testTextview.setText(myResponse);
                        }
                    });
                }
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
                if (response.isSuccessful()){
                    Logged = response.body().string();
                }
            }
        });

    }
    public void Login(View v) {
        Button login = (Button) findViewById(R.id.buttonSignIn);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Username;
                String Password;
                String fail = "failure";
                EditText UsernameText = (EditText)findViewById(R.id.inputUsername);
                EditText PasswordText = (EditText)findViewById(R.id.inputPassword);
                Username= UsernameText.getText().toString();
                Password= PasswordText.getText().toString();

                if (Username.indexOf('@')==-1){
                    //its a client
                    ClientLogin(Username,Password);
                    if (Logged.equals("failure")){
                        Toast.makeText(getApplicationContext(), "Incorrect Username or Password", Toast.LENGTH_LONG).show();
                    }
                    else{
                        Intent newAcc = new Intent(SignInActivity.this, ChatScreen.class);
                        startActivity(newAcc);
                    }
                }
                else{
                    CounsellorLogin(Username,Password);
                    if (Logged.equals("fai")){
                        Toast.makeText(getApplicationContext(), "Incorrect Username or Password", Toast.LENGTH_LONG).show();
                    }
                    else{
                        Intent newAcc = new Intent(SignInActivity.this, ChatScreen.class);
                        startActivity(newAcc);
                    }
                }
            }
        });

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