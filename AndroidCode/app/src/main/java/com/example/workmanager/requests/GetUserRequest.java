package com.example.workmanager.requests;

import java.io.Serializable;

public class GetUserRequest implements Serializable {
    Integer groupId;
    String username;

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
