package com.example.workmanager.apis;

import com.example.workmanager.requests.GetTaskRequest;
import com.example.workmanager.responses.GetTaskResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface TaskAPI {
    @POST ("api/Tasks/all")
    Call<GetTaskResponse> getAllTask(@Body GetTaskRequest request);
}
