package com.example.workmanager.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.workmanager.R;
import com.example.workmanager.dtos.UserDTO;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserViewHolder> {
    List<UserDTO> userList ;

    public UserAdapter(List<UserDTO> userList) {
        this.userList = userList;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View userView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_user_card,parent,false);
        UserViewHolder userViewHolder = new UserViewHolder(userView);
        return userViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        final UserDTO userDTO = userList.get(position);
        holder.getTxtFullname().setText(userDTO.getFullName());
        holder.getTxtUsername().setText(userDTO.getUsername());
        holder.getTxtRole().setText(userDTO.getRoleName());
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }
}