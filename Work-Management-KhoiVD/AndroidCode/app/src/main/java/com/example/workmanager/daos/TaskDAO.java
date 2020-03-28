package com.example.workmanager.daos;

import com.example.workmanager.apis.RetrofitClient;
import com.example.workmanager.apis.TaskAPI;


public class TaskDAO {
    TaskAPI taskAPI;

    public TaskDAO(){
        taskAPI = RetrofitClient.RetrofitClient().create(TaskAPI.class);
    }


}
