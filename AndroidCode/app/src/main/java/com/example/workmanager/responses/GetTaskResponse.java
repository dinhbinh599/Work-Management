package com.example.workmanager.responses;

import com.example.workmanager.dtos.TaskDTO;

import java.util.List;

public class GetTaskResponse extends BaseResponse {
    List<TaskDTO> data;

    public List<TaskDTO> getData() {
        return data;
    }

    public void setData(List<TaskDTO> data) {
        this.data = data;
    }
}
