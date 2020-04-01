package com.example.workmanager.responses;

import com.example.workmanager.dtos.GroupDTO;

public class GroupResponse extends BaseResponse {
    GroupDTO data;

    public GroupDTO getData() {
        return data;
    }

    public void setData(GroupDTO data) {
        this.data = data;
    }
}
