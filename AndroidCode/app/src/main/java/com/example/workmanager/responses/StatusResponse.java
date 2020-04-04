package com.example.workmanager.responses;

import com.example.workmanager.dtos.StatusDTO;

import java.util.List;

public class StatusResponse extends BaseResponse {

    List<StatusDTO> data;

    public List<StatusDTO> getStatusList() {
        return data;
    }

    public void setStatusList(List<StatusDTO> data) {
        this.data = data;
    }
}
