package com.example.mcproject.activities.adapters;

import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mcproject.activities.ChatActivity;
import com.example.mcproject.activities.ChatMessages;
import com.example.mcproject.activities.Constants;
import com.example.mcproject.activities.Users;
import com.example.mcproject.activities.UsersListActivity;
import com.example.mcproject.databinding.ItemContainerReceivedMessageBinding;
import com.example.mcproject.databinding.ItemContainerSentMessageBinding;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final List<ChatMessages> chatMessages;
    private final String senderID;
    private static final int View_Type_Sent = 1;
    private static final int View_Type_Received = 2;


    public ChatAdapter(List<ChatMessages> chatMessages, String senderID) {
        this.chatMessages = chatMessages;
        this.senderID = senderID;
    }



    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == View_Type_Sent){
            return new SentMessageViewHolder(
                    ItemContainerSentMessageBinding.inflate(
                            LayoutInflater.from(parent.getContext()),
                            parent,
                            false
                    )
            );
        }else {
            return new ReceivedMessageViewHolder(
                    ItemContainerReceivedMessageBinding.inflate(
                            LayoutInflater.from(parent.getContext()),
                            parent,
                            false
                    )
            );
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == View_Type_Sent){
            ((SentMessageViewHolder)holder).setData(chatMessages.get(position));
        }else{
            ((ReceivedMessageViewHolder)holder).setData(chatMessages.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return chatMessages.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (chatMessages.get(position).Sender_ID.equals(senderID)){
            return View_Type_Sent;
        }
        else {
            return View_Type_Received;
        }
    }

    static class SentMessageViewHolder extends RecyclerView.ViewHolder{

        private final ItemContainerSentMessageBinding binding;

        SentMessageViewHolder(ItemContainerSentMessageBinding itemContainerSentMessageBinding){
            super(itemContainerSentMessageBinding.getRoot());
            binding = itemContainerSentMessageBinding;
        }
        void setData(ChatMessages chatMessages){
            binding.textMessage.setText(chatMessages.Message);
            binding.dateTimeText.setText((chatMessages.DateSent));
        }
    }

    static class ReceivedMessageViewHolder extends RecyclerView.ViewHolder {

        private final ItemContainerReceivedMessageBinding binding;

        ReceivedMessageViewHolder(ItemContainerReceivedMessageBinding itemContainerReceivedMessageBinding){
            super(itemContainerReceivedMessageBinding.getRoot());
            binding = itemContainerReceivedMessageBinding;
        }

        void setData(ChatMessages chatMessages){
            binding.textMessage.setText(chatMessages.Message);
            binding.dateTimeText.setText(chatMessages.DateSent);
            binding.userTitle.setText(String.valueOf(chatMessages.Name.charAt(0)));
        }

    }
}
