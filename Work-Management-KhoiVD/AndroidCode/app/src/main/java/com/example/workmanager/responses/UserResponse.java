package com.example.workmanager.responses;

import com.example.workmanager.dtos.UserDTO;

public class UserResponse extends BaseResponse {
    UserDTO data;

    public UserDTO getData() {
        return data;
    }

    public void setData(UserDTO data) {
        this.data = data;
    }
}
