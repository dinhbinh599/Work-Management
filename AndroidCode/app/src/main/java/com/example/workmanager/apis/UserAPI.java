package com.example.workmanager.apis;

import com.example.workmanager.requests.LoginRequest;
import com.example.workmanager.requests.RegisterRequest;
import com.example.workmanager.responses.UserResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserAPI {

    @POST("api/Users/login")
    Call<UserResponse> login(@Body LoginRequest request);

    @POST("api/Users/register")
    Call<UserResponse> register(@Body RegisterRequest request);
}
