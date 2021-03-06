package com.example.workmanager.apis;

import com.example.workmanager.requests.CreateGroupRequest;
import com.example.workmanager.responses.GetGroupResponse;
import com.example.workmanager.responses.GroupResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface GroupAPI {
    @GET("api/Groups")
    Call<GetGroupResponse> getAllGroup();

    @GET("api/Groups/{id}")
    Call<GroupResponse> getGroup(@Path("id") int id);

    @POST("api/Groups")
    Call<GroupResponse> createGroup(@Body CreateGroupRequest request);

    @DELETE("api/Groups/{id}")
    Call<GroupResponse> deleteGroup(@Path("id") int id);
}
