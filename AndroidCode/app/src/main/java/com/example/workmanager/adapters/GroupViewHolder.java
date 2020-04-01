package com.example.workmanager.adapters;

import android.view.View;
import android.widget.TextView;
import com.example.workmanager.R;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class GroupViewHolder extends RecyclerView.ViewHolder {
    TextView txtName, txtDescription, txtCreatedTime, txtGroupId;
    public GroupViewHolder(@NonNull View itemView) {
        super(itemView);
        txtName = itemView.findViewById(R.id.txtGroupName);
        txtCreatedTime = itemView.findViewById(R.id.txtCreatedTime);
        txtGroupId = itemView.findViewById(R.id.txtGroupId);
        txtDescription = itemView.findViewById(R.id.txtDescription);
    }

    public TextView getTxtName() {
        return txtName;
    }

    public TextView getTxtDescription() {
        return txtDescription;
    }

    public TextView getTxtCreatedTime() {
        return txtCreatedTime;
    }

    public TextView getTxtGroupId() {
        return txtGroupId;
    }
}
