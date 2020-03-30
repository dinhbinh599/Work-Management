package com.example.workmanager.adapters;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.workmanager.R;

public class TaskViewHolder extends RecyclerView.ViewHolder {
    public TextView txtTaskName;
    public TextView txtDescription;
    public TextView txtEndTime;
    public TextView txtStatus;
    public View cardView;

    public TaskViewHolder(@NonNull View itemView) {
        super(itemView);
        cardView = itemView;
        txtTaskName = itemView.findViewById(R.id.txtTaskName);
        txtDescription = itemView.findViewById(R.id.txtDescription);
        txtEndTime = itemView.findViewById(R.id.txtEndTime);
        txtStatus = itemView.findViewById(R.id.txtStatusName);
    }

    public TextView getTxtTaskName() {
        return txtTaskName;
    }

    public void setTxtTaskName(TextView txtTaskName) {
        this.txtTaskName = txtTaskName;
    }

    public TextView getTxtDescription() {
        return txtDescription;
    }

    public void setTxtDescription(TextView txtDescription) {
        this.txtDescription = txtDescription;
    }

    public TextView getTxtEndTime() {
        return txtEndTime;
    }

    public void setTxtEndTime(TextView txtEndTime) {
        this.txtEndTime = txtEndTime;
    }

    public TextView getTxtStatus() {
        return txtStatus;
    }

    public void setTxtStatus(TextView txtStatus) {
        this.txtStatus = txtStatus;
    }

    public View getCardView() {
        return cardView;
    }

    public void setCardView(View cardView) {
        this.cardView = cardView;
    }
}
