package com.example.mcproject.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.mcproject.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class UsersListActivity extends AppCompatActivity {
    String Current_ID;
    String User_ID;
    String Type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_list);
        Intent users = getIntent();

        Current_ID = users.getStringExtra("Current_ID");
        User_ID = users.getStringExtra("User_ID ");
        Type = users.getStringExtra("Type");

        if (Type.equals("Counsellor")) {

            OkHttpClient client = new OkHttpClient();

            HttpUrl.Builder url_builder = HttpUrl.parse("https://lamp.ms.wits.ac.za/~s2465557/load_client_chats.php?").newBuilder();
            url_builder.addQueryParameter("Counsellor_ID", Current_ID);
            //need to send request variables/post parameters of datetime in this format from android
            String url = url_builder.build().toString();

            Request request = new Request.Builder().url(url).build();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                    e.printStackTrace();
                }

                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                    if (response.isSuccessful()) { //possible difference to mcproject signin
                        final String json = response.body().string();
                        UsersListActivity.this.runOnUiThread(new Runnable() { //possible difference to mcproject signin activity vs main activity here
                            @Override
                            public void run() {
                                try {
                                    processJSONCounsellor(json);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                }
            });
        }else
        {
            OkHttpClient client = new OkHttpClient();

            HttpUrl.Builder url_builder = HttpUrl.parse("https://lamp.ms.wits.ac.za/~s2465557/load_counsellor_chat.php?").newBuilder();
            String Client_ID = "";
            url_builder.addQueryParameter("Client_ID",Current_ID);
            //need to send request variables/post parameters of datetime in this format from android
            String url = url_builder.build().toString();

            Request request = new Request.Builder().url(url).build();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                    e.printStackTrace();
                }

                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                    if (response.isSuccessful()){ //possible difference to mcproject signin
                        final String json = response.body().string();
                        UsersListActivity.this.runOnUiThread(new Runnable() { //possible difference to mcproject signin activity vs main activity here
                            @Override
                            public void run() {
                                try {
                                    processJSONClient(json);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                }
            });

        }
    }

    public void processJSONCounsellor(String json) throws JSONException {
        //This is an array with only one element
        JSONArray jsonArray = new JSONArray(json);
        //extract each json object in the json array
        String all_client_details = "";
        for (int i = 0; i < jsonArray.length();i++){
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            String Client_username = jsonObject.getString("Client_name");
            String Chat_ID = jsonObject.getString("Chat_ID");
            String Last_Message = jsonObject.getString("Last_Message");
            all_client_details += Client_username + ", " + Chat_ID + ", "+Last_Message+ "\n";
        }
        //Store above values accordingly

    }

    public void processJSONClient(String json) throws JSONException {
        //This is an array with only one element
        JSONArray jsonArray = new JSONArray(json);
        //extract each json object in the json array
        String all_counsellor_details;

        JSONObject jsonObject = jsonArray.getJSONObject(0);
        String Counsellor_name = jsonObject.getString("Counsellor_name");
        String Chat_ID = jsonObject.getString("Chat_ID");
        String Last_Message = jsonObject.getString("Last_Message");
        all_counsellor_details = Counsellor_name + ", " + Chat_ID+ ", "+Last_Message;
        //Store above values accordingly


    }
}