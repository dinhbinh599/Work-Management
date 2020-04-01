package com.example.workmanager.requests;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CreateGroupRequest implements Serializable {
    @SerializedName("userId")
    int userId;
    @SerializedName("description")
    String description;
    @SerializedName("name")
    String name;

    public CreateGroupRequest() {
    }

    public CreateGroupRequest(int userId, String description, String name) {
        this.userId = userId;
        this.description = description;
        this.name = name;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
