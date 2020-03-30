package com.example.workmanager.adapters;

import android.content.Context;
import android.graphics.Color;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.workmanager.R;
import com.example.workmanager.dtos.TaskDTO;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskViewHolder> {
   private List<TaskDTO> taskList;
   Context context;

    public TaskAdapter(List<TaskDTO> taskList) {
        this.taskList = taskList;

    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View taskView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_task_card, parent, false);
        TaskViewHolder taskViewHolder = new TaskViewHolder(taskView);
        return taskViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        holder.getTxtTaskName().setText(taskList.get(position).getName());
        holder.getTxtDescription().setText(taskList.get(position).getDescription());
        holder.getTxtStatus().setText(taskList.get(position).getStatusName());
        switch (taskList.get(position).getStatusName()){
            case "Processing":{
                holder.getTxtStatus().setTextColor(Color.CYAN);
                break;
            }
            case "Not started":{
                holder.getTxtStatus().setTextColor(Color.GRAY);
            }
            case "Finished":{
                holder.getTxtStatus().setTextColor(Color.GREEN);
                break;
            }
            case "Rejected":{
                holder.getTxtStatus().setTextColor(Color.MAGENTA);
                break;
            }
            case "Failed":{
                holder.getTxtStatus().setTextColor(Color.RED);
                break;
            }
            default:{
                holder.getTxtStatus().setTextColor(Color.BLUE);
                break;
            }
        }
        Date endTime = taskList.get(position).getEndTime();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        holder.getTxtEndTime().setText(endTime != null ? sdf.format(endTime) : "");
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }
}
