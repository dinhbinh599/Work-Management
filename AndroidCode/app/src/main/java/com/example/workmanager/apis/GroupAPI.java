package com.example.workmanager.apis;

import com.example.workmanager.requests.CreateGroupRequest;
import com.example.workmanager.responses.GetGroupResponse;
import com.example.workmanager.responses.GroupResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface GroupAPI {
    @GET("api/Groups")
    Call<GetGroupResponse> getAllGroup();

    @POST("api/Groups")
    Call<GroupResponse> createGroup(@Body CreateGroupRequest request);
}
