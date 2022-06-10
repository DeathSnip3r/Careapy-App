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

public class ForgotPassword extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
    }

    public void next_ChangePass(View v){

        TextView UsernameText = (TextView) findViewById(R.id.inputUsername);
        TextView BackupPinText = (TextView) findViewById(R.id.inputConfirmNewPass);

        String Username = UsernameText.getText().toString();
        String BackupPin = BackupPinText.getText().toString();

        if (Username.indexOf('@')>=0){
            CounsellorPassword(Username,BackupPin);
        }
        else{
            ClientPassword(Username, BackupPin);
        }

    }

    public void CounsellorPassword(String Username, String BackupPin){

        OkHttpClient client = new OkHttpClient();

        HttpUrl.Builder urlBuilder = HttpUrl.parse("https://lamp.ms.wits.ac.za/~s2465557/forgot_password_counsellor.php?").newBuilder();
        urlBuilder.addQueryParameter("Counsellor_Email", Username);
        urlBuilder.addQueryParameter("Counsellor_Safety_Pin", BackupPin);
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
                ForgotPassword.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        if (myResponse.equals("failure")){
                            Toast.makeText(getApplicationContext(), "Incorrect Username or Backup Pin", Toast.LENGTH_LONG).show();
                        }
                        else{
                            Intent newAcc = new Intent(ForgotPassword.this, change_Password.class);
                            startActivity(newAcc);
                            newAcc.putExtra("Username", Username);
                            newAcc.putExtra("Backup Pin", BackupPin);
                            newAcc.putExtra("Type","Counsellor");
                            // Start the new activity.
                            startActivity(newAcc);
                            finish();
                        }
                    }
                });

            }
        });

    }

    public void ClientPassword(String Username, String BackupPin){

        OkHttpClient client = new OkHttpClient();

        HttpUrl.Builder urlBuilder = HttpUrl.parse("https://lamp.ms.wits.ac.za/~s2465557/forgot_password_client.php?").newBuilder();
        urlBuilder.addQueryParameter("Client_Username", Username);
        urlBuilder.addQueryParameter("Client_Safety_Pin", BackupPin);
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
                ForgotPassword.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        if (myResponse.equals("failure")){
                            Toast.makeText(getApplicationContext(), "Incorrect Username or Backup Pin", Toast.LENGTH_LONG).show();
                        }
                        else{
                            Intent newAcc = new Intent(ForgotPassword.this, change_Password.class);
                            startActivity(newAcc);
                            newAcc.putExtra("Username", Username);
                            newAcc.putExtra("Backup Pin", BackupPin);
                            newAcc.putExtra("Type","Client");
                            // Start the new activity.
                            startActivity(newAcc);
                            finish();
                        }
                    }
                });

            }
        });

    }

}