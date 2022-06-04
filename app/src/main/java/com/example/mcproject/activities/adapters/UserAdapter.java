package com.example.mcproject.activities.adapters;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mcproject.activities.UserListener;
import com.example.mcproject.activities.Users;
import com.example.mcproject.databinding.ItemContainerUserBinding;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserHolder>{

    private final List<Users> users;
    private final UserListener userListener;

    public UserAdapter(List<Users> users, UserListener userListener) {
        this.users = users;
        this.userListener = userListener;
    }

    @NonNull
    @Override
    public UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemContainerUserBinding itemContainerUserBinding = ItemContainerUserBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        );
        return new UserHolder(itemContainerUserBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull UserHolder holder, int position) {
        holder.setUserData(users.get(position));
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    class UserHolder extends RecyclerView.ViewHolder{

        ItemContainerUserBinding binding;

        UserHolder(ItemContainerUserBinding itemContainerUserBinding){
            super(itemContainerUserBinding.getRoot());
            binding = itemContainerUserBinding;
        }
        void setUserData(Users user){
            binding.txtName.setText(user.name);
            binding.txtRecentMsg.setText(user.RecentMsg);
            binding.title.setText(String.valueOf(user.name.charAt(0)));
            binding.getRoot().setOnClickListener(view -> userListener.onClickUsers(user));
        }
    }
}
