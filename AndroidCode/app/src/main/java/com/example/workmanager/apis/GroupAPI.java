package com.example.workmanager.apis;

import com.example.workmanager.responses.GetGroupResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GroupAPI {
    @GET("api/Groups")
    Call<GetGroupResponse> getAllGroup();
}
