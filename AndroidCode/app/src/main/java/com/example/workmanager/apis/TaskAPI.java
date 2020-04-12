package com.example.workmanager.apis;

import com.example.workmanager.requests.CreateTaskRequest;
import com.example.workmanager.requests.GetTaskRequest;
import com.example.workmanager.requests.UpdateTaskRequest;
import com.example.workmanager.responses.GetTaskResponse;
import com.example.workmanager.responses.TaskResponse;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface TaskAPI {
    @POST ("api/Tasks/all")
    Call<GetTaskResponse> getAllTask(@Body GetTaskRequest request);

    @GET("api/Tasks/{id}")
    Call<TaskResponse> getTaskById(@Path("id") int id);

    @POST("api/Tasks")
    Call<TaskResponse> createTask(@Body CreateTaskRequest request);

    @PUT("api/Tasks/{id}")
    Call<TaskResponse> updateTask(@Path("id") int id, @Body UpdateTaskRequest request);

    @DELETE("api/Tasks/{id}")
    Call<TaskResponse> deleteTask(@Path("id") int id);

    @Multipart
    @POST("api/Tasks/upload")
    Call<TaskResponse> uploadConfirmation(@Part MultipartBody.Part image);
}
