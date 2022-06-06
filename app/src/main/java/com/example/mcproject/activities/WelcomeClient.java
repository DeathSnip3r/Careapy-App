package com.example.mcproject.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.mcproject.R;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class WelcomeClient extends AppCompatActivity {
    String Client_ID;
    String Religion;
    String Language;
    String BackupPin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcom_client);
        Intent regAcc1 = getIntent();
        Client_ID = regAcc1.getStringExtra("Client_ID");
        Client_ID = Client_ID.replaceAll("[\\n\\t ]", "");
        Religion = regAcc1.getStringExtra("Client_Religion_Selected");
        Language = regAcc1.getStringExtra("Client_Language");
        BackupPin = regAcc1.getStringExtra("Backup Pin");
        assignCounsellor();


    }

    public void LoadPage(View v){

        addchat();
        Intent RegClient = new Intent(WelcomeClient.this, SignInActivity.class);
         //Start the new activity.
        startActivity(RegClient);

    }

    public void assignCounsellor(){
        OkHttpClient client = new OkHttpClient();
        HttpUrl.Builder urlBuilder = HttpUrl.parse("https://lamp.ms.wits.ac.za/~s2465557/match_algorithm.php?").newBuilder();
        urlBuilder.addQueryParameter("Client_ID", Client_ID);
        urlBuilder.addQueryParameter("Client_Language", Language);
        urlBuilder.addQueryParameter("Client_Religion_Selected", Religion);
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

    public void addchat(){

        OkHttpClient client = new OkHttpClient();

        HttpUrl.Builder urlBuilder = HttpUrl.parse("https://lamp.ms.wits.ac.za/~s2465557/create_chat.php?").newBuilder();
        urlBuilder.addQueryParameter("Client_ID", Client_ID);
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