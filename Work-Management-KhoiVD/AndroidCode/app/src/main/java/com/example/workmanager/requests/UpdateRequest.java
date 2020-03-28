package com.example.workmanager.requests;

import com.google.gson.annotations.SerializedName;

public class UpdateRequest {
    @SerializedName("userId")
    public int userId;
    @SerializedName("username")
    public String username;
    @SerializedName("password")
    public String password;
    @SerializedName("fullname")
    public String fullname;
    @SerializedName("email")
    public String email;

    public UpdateRequest() {
    }

    public UpdateRequest(int userId, String username, String password, String fullname, String email) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.email = email;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
