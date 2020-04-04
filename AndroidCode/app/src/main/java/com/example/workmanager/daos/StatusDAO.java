package com.example.workmanager.daos;

import com.example.workmanager.apis.RetrofitClient;
import com.example.workmanager.apis.StatusAPI;
import com.example.workmanager.responses.StatusResponse;

import java.io.Serializable;

import retrofit2.Callback;

public class StatusDAO implements Serializable {
    StatusAPI statusAPI;
    public StatusDAO() {
        statusAPI = RetrofitClient.RetrofitClient().create(StatusAPI.class);
    }
    public void getAllStatus(String name, Callback<StatusResponse> callBack){
        statusAPI.getAllStatus(name).enqueue(callBack);
    }
}
