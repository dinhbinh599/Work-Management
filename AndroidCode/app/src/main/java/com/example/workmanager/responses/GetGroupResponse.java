package com.example.workmanager.responses;

import com.example.workmanager.dtos.GroupDTO;

import java.util.List;

public class GetGroupResponse extends BaseResponse {
    List<GroupDTO> data;

    public List<GroupDTO> getData() {
        return data;
    }

    public void setData(List<GroupDTO> data) {
        this.data = data;
    }
}
