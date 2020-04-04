package com.example.workmanager.adapters;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.workmanager.GroupDetailFragment;
import com.example.workmanager.MainActivity;
import com.example.workmanager.R;
import com.example.workmanager.dtos.GroupDTO;

import java.text.SimpleDateFormat;
import java.util.List;

public class GroupAdapter extends RecyclerView.Adapter<GroupViewHolder> {
    List<GroupDTO> groupList;

    public GroupAdapter(List<GroupDTO> groupList) {
        this.groupList = groupList;
    }

    @NonNull
    @Override
    public GroupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View groupView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_group_card,parent,false);
        GroupViewHolder groupViewHolder = new GroupViewHolder(groupView);
        return groupViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull GroupViewHolder holder, int position) {
        final GroupDTO groupDTO = groupList.get(position);
        holder.getTxtName().setText(groupDTO.getName());
        holder.getTxtDescription().setText(groupDTO.getDescription());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        holder.getTxtCreatedTime().setText(sdf.format(groupDTO.getCreatedTime()));
        holder.getTxtGroupId().setText(groupDTO.getGroupId()+"");
        holder.itemView.setOnClickListener((v)->{
            GroupDetailFragment fragment = new GroupDetailFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("groupId",groupDTO.getGroupId());
            fragment.setArguments(bundle);
            MainActivity mainActivity = (MainActivity)v.getContext();
            mainActivity.getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,fragment).commit();
        });
    }

    @Override
    public int getItemCount() {
        return groupList.size();
    }
}
