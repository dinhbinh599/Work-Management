package com.example.workmanager.responses;

import com.example.workmanager.dtos.TaskDTO;

public class TaskResponse extends BaseResponse {
    TaskDTO data;

    public TaskDTO getData() {
        return data;
    }

    public void setData(TaskDTO data) {
        this.data = data;
    }
}
