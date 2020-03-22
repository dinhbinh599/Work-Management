package com.example.workmanager.daos;

import com.example.workmanager.apis.RetrofitClient;
import com.example.workmanager.apis.UserAPI;
import com.example.workmanager.requests.LoginRequest;
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
}
