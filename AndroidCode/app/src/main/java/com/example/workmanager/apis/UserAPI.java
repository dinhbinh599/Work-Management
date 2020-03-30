package com.example.workmanager.apis;

import com.example.workmanager.requests.LoginRequest;
import com.example.workmanager.requests.RegisterRequest;
import com.example.workmanager.requests.UpdateRequest;
import com.example.workmanager.responses.UserResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UserAPI {

    @POST("api/Users/login")
    Call<UserResponse> login(@Body LoginRequest request);

    @POST("api/Users/register")
    Call<UserResponse> register(@Body RegisterRequest request);

    @PUT("api/Users/update")
    Call<UserResponse> update(@Body UpdateRequest request);

    @GET("api/Users/{id}")
    Call<UserResponse> getUserById(@Path("id") int id);
}
