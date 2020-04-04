package com.example.workmanager.responses;

import com.example.workmanager.dtos.UserDTO;

import java.util.List;

public class GetUserResponse extends BaseResponse {
    List<UserDTO> data;

    public List<UserDTO> getData() {
        return data;
    }

    public void setData(List<UserDTO> data) {
        this.data = data;
    }
}
