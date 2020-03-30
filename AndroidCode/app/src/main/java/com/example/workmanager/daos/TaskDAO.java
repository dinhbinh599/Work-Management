package com.example.workmanager.daos;

import com.example.workmanager.apis.RetrofitClient;
import com.example.workmanager.apis.TaskAPI;
import com.example.workmanager.requests.GetTaskRequest;
import com.example.workmanager.responses.GetTaskResponse;

import retrofit2.Callback;


public class TaskDAO {
    TaskAPI taskAPI;

    public TaskDAO(){
        taskAPI = RetrofitClient.RetrofitClient().create(TaskAPI.class);
    }

    public void getAllTask(GetTaskRequest request, Callback<GetTaskResponse> callBack){
       taskAPI.getAllTask(request).enqueue(callBack);
    }

}
