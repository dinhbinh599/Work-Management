package com.example.workmanager.apis;

import com.example.workmanager.responses.StatusResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface StatusAPI {

    @GET ("api/status")
    Call<StatusResponse> getAllStatus(@Query("name") String name);
}
