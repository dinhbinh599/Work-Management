package com.example.workmanager.adapters;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.workmanager.R;

public class UserViewHolder extends RecyclerView.ViewHolder {
    TextView txtFullname,txtUsername,txtRole;

    public UserViewHolder(@NonNull View itemView) {
        super(itemView);
        txtFullname = itemView.findViewById(R.id.txtFullName);
        this.txtUsername = itemView.findViewById(R.id.txtUsername);
        this.txtRole = itemView.findViewById(R.id.txtRole);
    }

    public TextView getTxtFullname() {
        return txtFullname;
    }

    public void setTxtFullname(TextView txtFullname) {
        this.txtFullname = txtFullname;
    }

    public TextView getTxtUsername() {
        return txtUsername;
    }

    public void setTxtUsername(TextView txtUsername) {
        this.txtUsername = txtUsername;
    }

    public TextView getTxtRole() {
        return txtRole;
    }

    public void setTxtRole(TextView txtRole) {
        this.txtRole = txtRole;
    }
}
