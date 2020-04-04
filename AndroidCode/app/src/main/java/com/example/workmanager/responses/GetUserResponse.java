package com.example.workmanager.responses;

import com.example.workmanager.dtos.UserDTO;

import java.io.Serializable;
import java.util.List;

public class GetUserResponse implements Serializable {
    List<UserDTO> data;

    public void setData(List<UserDTO> data) {
        this.data = data;
    }

    public List<UserDTO> getData() {
        return data;
    }
}
