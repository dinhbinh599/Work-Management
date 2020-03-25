package com.example.workmanager.requests;

import com.google.gson.annotations.SerializedName;

public class RegisterRequest {
    @SerializedName("username")
    public String username;
    @SerializedName("password")
    public String password;
    @SerializedName("fullname")
    public String fullname;
    @SerializedName("email")
    public String email;
    @SerializedName("roleId")
    public int roleId;
    @SerializedName("groupId")
    public int groupId;

    public RegisterRequest() {
    }

    public RegisterRequest(String username, String password, String fullname, String email, int roleId, int groupId) {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.email = email;
        this.roleId = roleId;
        this.groupId = groupId;
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

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }
}
