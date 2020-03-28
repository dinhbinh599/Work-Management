package com.example.workmanager.adapters;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.workmanager.R;
import com.example.workmanager.dtos.TaskDTO;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskViewHolder> {
   private List<TaskDTO> taskList;
   Context context;

    public TaskAdapter(List<TaskDTO> taskList, Context context) {
        this.taskList = taskList;
        this.context = context;
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

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
