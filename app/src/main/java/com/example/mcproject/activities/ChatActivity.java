package com.example.mcproject.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import com.example.mcproject.R;
import com.example.mcproject.activities.adapters.ChatAdapter;
import com.example.mcproject.activities.adapters.UserAdapter;
import com.example.mcproject.databinding.ActivityChatScreenBinding;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ChatActivity extends AppCompatActivity {

    private ActivityChatScreenBinding binding;
    private Users recipientUser;
    private List<ChatMessages> chatMessages;
    private ChatAdapter chatAdapter;
    String User_ID;
    String Chat_ID;
    String LastMessageID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent = getIntent();
        User_ID = intent.getStringExtra("User_ID");
        Chat_ID = intent.getStringExtra("Chat_ID");
        User_ID = User_ID.replaceAll("[\n\t ]", "");
        setListener();
        loadRecipientData();
        init();
        LoadMessages();

        //Realtime Chat
        final Handler handler = new Handler();
        final int delay = 2000; // 1000 milliseconds == 1 second

        handler.postDelayed(new Runnable() {
            public void run() {

                OkHttpClient client = new OkHttpClient();

                HttpUrl.Builder urlBuilder = HttpUrl.parse("https://lamp.ms.wits.ac.za/~s2465557/load_messages.php?").newBuilder();
                //urlBuilder.addQueryParameter("Last_Message_ID", LastMessageID);
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
                            //try {
                                String myResponse = response.body().string();
                                /*List<ChatMessages> Messages = new ArrayList<>();
                                for (int i = 0; i < myResponse.length(); i++) {
                                    // Create a json object from the array
                                    JSONObject jsonObject = myResponse.getJSONObject(i);
                                    // Get the values from the json object
                                    String Sender_ID = jsonObject.getString("Sender_ID");

                                    String Message_Text = jsonObject.getString("Message_Text");

                                    String DateTimeSent = jsonObject.getString("DateTimeSent");
                                    ChatMessages msg = new ChatMessages();
                                    msg.Message = Message_Text;
                                    msg.Sender_ID = Sender_ID;
                                    msg.DateSent = DateTimeSent;
                                    Messages.add(msg);


                                    if (i==myResponse.length()-1){
                                        LastMessageID=jsonObject.getString("Message_ID");
                                    }
                                    ChatAdapter chatAdapter = new ChatAdapter(Messages, User_ID);
                                    binding.chatRecyclerView.setAdapter(chatAdapter);
                                    binding.chatRecyclerView.setVisibility(View.VISIBLE);
                                }*/
                                ChatActivity.this.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            messageBox(myResponse);
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });
                           // }

                        }
                    }
                });
                handler.postDelayed(this, delay);
            }
        }, delay);
    }

     public void LoadMessages() {
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
                if (response.isSuccessful()) {
                    String myResponse = response.body().string();
                    ChatActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //run for loop and load all messages here
                            try {
                                messageBox(myResponse);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });

                }
            }
        });
    }
        private  void  messageBox(String json) throws  JSONException {
            JSONArray jsonArray = new JSONArray(json);
            List<ChatMessages> Messages = new ArrayList<>();
            int count = chatMessages.size();
            for (int i = 0; i < jsonArray.length(); i++) {
                // Create a json object from the array
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                // Get the values from the json object
                String Sender_ID = jsonObject.getString("Sender_ID");
                String Message_Text = jsonObject.getString("Message_Text");
                String DateTimeSent = jsonObject.getString("DateTimeSent");

                // populate array here
                ChatMessages msg = new ChatMessages();
                msg.Sender_ID = Sender_ID;
                msg.Message = Message_Text;
                msg.DateSent = DateTimeSent;
                Messages.add(msg);

                if (i == jsonArray.length() - 1) {
                    LastMessageID = jsonObject.getString("Message_ID");
                }
                ChatAdapter chatAdapter = new ChatAdapter(Messages, User_ID);
                binding.chatRecyclerView.setAdapter(chatAdapter);
                binding.chatRecyclerView.setVisibility(View.VISIBLE);
            }
                if (count == 0) {
                    chatAdapter.notifyDataSetChanged();
                } else {
                    chatAdapter.notifyItemRangeInserted(chatMessages.size(), chatMessages.size());
                    binding.chatRecyclerView.smoothScrollToPosition(chatMessages.size() - 1);
                }
                binding.chatRecyclerView.setVisibility(View.VISIBLE);

            binding.progressBar.setVisibility(View.GONE);
        }

        private void init(){
            chatMessages = new ArrayList<>();
            chatAdapter = new ChatAdapter(
                    chatMessages,
                    User_ID //senderId
            );
            binding.chatRecyclerView.setAdapter(chatAdapter);
            //php script of relevant data
        }

        private void sendMessage(){
            EditText mMessage = findViewById(R.id.inputMessage);
            String Message = mMessage.getText().toString();
            String pattern = "yyyy-MM-dd HH:mm:ss";
            String date = new SimpleDateFormat(pattern, Locale.getDefault()).format(new Date());
            if(!mMessage.getText().toString().isEmpty()){
                OkHttpClient client = new OkHttpClient();

                HttpUrl.Builder urlBuilder = HttpUrl.parse("https://lamp.ms.wits.ac.za/~s2465557/insert_message.php?").newBuilder();
                urlBuilder.addQueryParameter("Chat_ID",Chat_ID );
                urlBuilder.addQueryParameter("Sender_ID", User_ID);
                urlBuilder.addQueryParameter("Message_Text", Message);
                urlBuilder.addQueryParameter("Date_Time_Sent", date);
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
                        if (response.isSuccessful()) {
                            ChatActivity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    // should send message to database i think
                                }
                            });
                        }
                    }
                });
            }
            binding.inputMessage.setText(null);
        }


        private void loadRecipientData(){
            recipientUser = (Users) getIntent().getSerializableExtra(Constants.KEY_USER);
            binding.textUsername.setText(recipientUser.name);
        }

       private void setListener(){
            binding.imageBack.setOnClickListener(view -> onBackPressed());
            binding.layoutSend.setOnClickListener(view -> sendMessage());
        }

    }
