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
    @SerializedName("groubId")
    public int groubId;

    public RegisterRequest() {
    }

    public RegisterRequest(String username, String password, String fullname, String email, int roleId, int groubId) {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.email = email;
        this.roleId = roleId;
        this.groubId = groubId;
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

    public int getGroubId() {
        return groubId;
    }

    public void setGroubId(int groubId) {
        this.groubId = groubId;
    }
}
