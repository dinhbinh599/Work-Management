package com.example.workmanager.daos;

import com.example.workmanager.apis.RetrofitClient;
import com.example.workmanager.apis.UserAPI;
import com.example.workmanager.requests.GetUserRequest;
import com.example.workmanager.requests.LoginRequest;
import com.example.workmanager.requests.RegisterRequest;
import com.example.workmanager.requests.UpdateRequest;
import com.example.workmanager.responses.GetUserResponse;
import com.example.workmanager.responses.UserResponse;

import retrofit2.Callback;


public class UserDAO {
    UserAPI userAPI;

    public UserDAO() {
        userAPI = RetrofitClient.RetrofitClient().create(UserAPI.class);

    }
    public void checkLogin(LoginRequest request, Callback<UserResponse> callBack)
    {
        userAPI.login(request).enqueue(callBack);
    }
    public void register(RegisterRequest request, Callback<UserResponse> callback){
        userAPI.register(request).enqueue(callback);
    }
    public void getAllUser(GetUserRequest request, Callback<GetUserResponse> callBack) {
        userAPI.getAllUser(request).enqueue(callBack);
    }


    public void update(UpdateRequest request, Callback<UserResponse> callback){
        userAPI.update(request).enqueue(callback);
    }
    public void getUserProfile(int userId, Callback<UserResponse> callback){
        userAPI.getUserById(userId).enqueue(callback);
    }
}
