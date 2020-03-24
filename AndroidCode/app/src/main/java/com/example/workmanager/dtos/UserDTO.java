package com.example.workmanager.dtos;

import com.google.gson.annotations.SerializedName;

public class UserDTO {
    @SerializedName("userId")
    int UserId;
    @SerializedName("roleId")
    int roleId;
    @SerializedName("fullname")
    String fullname;
    @SerializedName("email")
    String email;
    @SerializedName("groupId")
    int groupId;

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
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

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

}
