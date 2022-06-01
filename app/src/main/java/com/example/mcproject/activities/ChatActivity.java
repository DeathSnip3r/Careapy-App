package com.example.mcproject.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mcproject.R;
import com.example.mcproject.databinding.ActivityChatScreenBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ChatActivity extends AppCompatActivity {

    private PreferenceManager preferenceManager;

    String Chat_ID="3";
    String User_ID;
    String LastMessageID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_screen);
        Intent chat = getIntent();

        //Chat_ID = regAcc.getStringExtra("Chat_ID");
        User_ID = chat.getStringExtra("User_ID ");


       //LOAD MESSAGES
        OkHttpClient client = new OkHttpClient();

        HttpUrl.Builder urlBuilder = HttpUrl.parse("https://lamp.ms.wits.ac.za/~s2465557/load_messages.php?").newBuilder();
        urlBuilder.addQueryParameter("Chat_ID", Chat_ID);
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
                       try {
                       JSONArray myResponse = new JSONArray(response.body().string());

                        StringBuilder result = new StringBuilder();
                            for (int i = 0; i < myResponse.length(); i++) {
                                // Create a json object from the array
                                JSONObject jsonObject = myResponse.getJSONObject(i);
                                // Get the values from the json object
                                String Sender_ID = jsonObject.getString("Sender_ID");

                                String Message_Text = jsonObject.getString("Message_Text");

                                String DateTimeSent = jsonObject.getString("DateTimeSent");

                                // populate array here
                                if (Sender_ID.equals(User_ID)){
                                    //call container sent
                                    //container.text = Message_Text
                                    //container.datetime= DateTimeSent
                                }else{
                                    //call container sent
                                    //container.text = Message_Text
                                    //container.datetime= DateTimeSent
                                }

                                // Create a string to store the output
                                String outputLine = Sender_ID + ", " + Message_Text + ", " + DateTimeSent;
                                // Add the output line to the output string
                                result.append(outputLine).append("/n");

                                if (i==myResponse.length()-1){
                                    LastMessageID=jsonObject.getString("Message_ID");
                                }
                            }
                            ChatActivity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    //run for loop and load all messages here
                                    EditText t = (EditText) findViewById(R.id.inputMessage);
                                    t.setText(LastMessageID);
                                }
                            });
                        }catch (JSONException e) {
                            e.printStackTrace();
                        }
                }
            }
            });

        final Handler handler = new Handler();
        final int delay = 2000; // 1000 milliseconds == 1 second

        handler.postDelayed(new Runnable() {
            public void run() {

                OkHttpClient client = new OkHttpClient();

                HttpUrl.Builder urlBuilder = HttpUrl.parse("https://lamp.ms.wits.ac.za/~s2465557/real_time.php?").newBuilder();
                urlBuilder.addQueryParameter("Last_Message_ID", LastMessageID);
                urlBuilder.addQueryParameter("Chat_ID", Chat_ID);
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
                            try {
                                JSONArray myResponse = new JSONArray(response.body().string());

                                StringBuilder result = new StringBuilder();
                                for (int i = 0; i < myResponse.length(); i++) {
                                    // Create a json object from the array
                                    JSONObject jsonObject = myResponse.getJSONObject(i);
                                    // Get the values from the json object
                                    String Sender_ID = jsonObject.getString("Sender_ID");

                                    String Message_Text = jsonObject.getString("Message_Text");

                                    String DateTimeSent = jsonObject.getString("DateTimeSent");

                                    if (Sender_ID.equals(User_ID)){
                                        //call container sent
                                        //container.text = Message_Text
                                        //container.datetime= DateTimeSent
                                    }else{
                                        //call container sent
                                        //container.text = Message_Text
                                        //container.datetime= DateTimeSent
                                    }

                                    // Create a string to store the output
                                    String outputLine = Sender_ID + ", " + Message_Text + ", " + DateTimeSent;
                                    // Add the output line to the output string
                                    result.append(outputLine).append("/n");

                                    if (i==myResponse.length()-1){
                                        LastMessageID=jsonObject.getString("Message_ID");
                                    }
                                }
                                ChatActivity.this.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        // where you would add the container message
                                        EditText t = (EditText) findViewById(R.id.inputMessage);
                                        t.setText(LastMessageID);
                                    }
                                });
                            }catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });

                handler.postDelayed(this, delay);
            }
        }, delay);
        }



        private void init(){

        }
    }
