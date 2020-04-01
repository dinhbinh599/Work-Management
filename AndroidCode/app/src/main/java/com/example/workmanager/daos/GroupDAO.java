package com.example.workmanager.daos;

import com.example.workmanager.apis.GroupAPI;
import com.example.workmanager.apis.RetrofitClient;
import com.example.workmanager.requests.CreateGroupRequest;
import com.example.workmanager.responses.GetGroupResponse;
import com.example.workmanager.responses.GroupResponse;

import retrofit2.Callback;


public class GroupDAO {
    GroupAPI groupAPI;

    public GroupDAO() { groupAPI = RetrofitClient.RetrofitClient().create(GroupAPI.class); }

    public void createGroup(CreateGroupRequest request, Callback<GroupResponse> callback){
        groupAPI.createGroup(request).enqueue(callback);
    }

    public void getAllGroup(Callback<GetGroupResponse> callback){
        groupAPI.getAllGroup().enqueue(callback);
    }

}
