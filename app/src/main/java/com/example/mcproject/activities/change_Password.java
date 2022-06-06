package com.example.mcproject.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

public class change_Password extends AppCompatActivity {

    String Username;
    String BackupPin;
    String Type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        Intent intent = getIntent();
        Username= intent.getStringExtra("Username");
        BackupPin = intent.getStringExtra("Backup Pin");
        Type = intent.getStringExtra("Type");
    }

    public void ChangePass(View v){

        TextView NewPassText = (TextView)findViewById(R.id.inputNewPassword);
        TextView NewConPassText = (TextView) findViewById(R.id.inputConfirmNewPass);

        String NewPass = NewPassText.getText().toString();
        String NewConPass = NewConPassText.getText().toString();

        if (NewPass.equals(NewConPass)){
            if (Type.equals("Counsellor")){
                CounsellorPassword(NewPass);
            }
            else{
                ClientPassword(NewPass);
            }
            Toast.makeText(getApplicationContext(), "Password Changed", Toast.LENGTH_LONG).show();
            Intent newAcc = new Intent(change_Password.this, SignInActivity.class);
            startActivity(newAcc);
        }else
        {
            Toast.makeText(getApplicationContext(), "Passwords do not match", Toast.LENGTH_LONG).show();
        }
    }

    public void CounsellorPassword(String NewPass){

        OkHttpClient client = new OkHttpClient();

        HttpUrl.Builder urlBuilder = HttpUrl.parse("https://lamp.ms.wits.ac.za/~s2465557/counsellor_password_change.php?").newBuilder();
        urlBuilder.addQueryParameter("Counsellor_Email", Username);
        urlBuilder.addQueryParameter("Counsellor_Safety_Pin", BackupPin);
        urlBuilder.addQueryParameter("New_Password", NewPass);
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

            }
        });

    }

    public void ClientPassword(String NewPass){

        OkHttpClient client = new OkHttpClient();

        HttpUrl.Builder urlBuilder = HttpUrl.parse("https://lamp.ms.wits.ac.za/~s2465557/client_password_change.php?").newBuilder();
        urlBuilder.addQueryParameter("Client_Username", Username);
        urlBuilder.addQueryParameter("Client_Safety_Pin", BackupPin);
        urlBuilder.addQueryParameter("New_Password", NewPass);
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

            }
        });

    }
}