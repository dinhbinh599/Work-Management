package com.example.workmanager.requests;

import com.google.gson.annotations.SerializedName;

public class UpdateRequest {
    @SerializedName("userId")
    public int userId;
    @SerializedName("fullname")
    public String fullname;
    @SerializedName("email")
    public String email;
    @SerializedName("phone")
    public String phone;
    @SerializedName("roleName")
    public String roleName;
    public UpdateRequest() {
    }

    public UpdateRequest(int userId,String fullname, String email, String phone, String roleName) {
        this.userId = userId;
        this.fullname = fullname;
        this.email = email;
        this.phone = phone;
        this.roleName = roleName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
