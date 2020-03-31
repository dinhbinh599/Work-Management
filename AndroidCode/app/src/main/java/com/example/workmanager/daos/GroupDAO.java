package com.example.workmanager.daos;

import com.example.workmanager.apis.GroupAPI;
import com.example.workmanager.apis.RetrofitClient;
import com.example.workmanager.responses.GetGroupResponse;
import retrofit2.Callback;


public class GroupDAO {
    GroupAPI groupAPI;

    public GroupDAO() { groupAPI = RetrofitClient.RetrofitClient().create(GroupAPI.class); }

    public void getAllGroup(Callback<GetGroupResponse> callback){
        groupAPI.getAllGroup().enqueue(callback);
    }

}
