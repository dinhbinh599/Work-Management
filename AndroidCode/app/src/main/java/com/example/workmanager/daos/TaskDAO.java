package com.example.workmanager.daos;

import com.example.workmanager.apis.RetrofitClient;
import com.example.workmanager.apis.TaskAPI;
import com.example.workmanager.requests.CreateTaskRequest;
import com.example.workmanager.requests.GetTaskRequest;
import com.example.workmanager.requests.UpdateTaskRequest;
import com.example.workmanager.responses.GetTaskResponse;
import com.example.workmanager.responses.TaskResponse;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Callback;


public class TaskDAO {
    TaskAPI taskAPI;

    public TaskDAO(){
        taskAPI = RetrofitClient.RetrofitClient().create(TaskAPI.class);
    }

    public void getAllTask(GetTaskRequest request, Callback<GetTaskResponse> callBack){
       taskAPI.getAllTask(request).enqueue(callBack);
    }
    public void getTaskDetail(int id, Callback<TaskResponse> callBack) {
        taskAPI.getTaskById(id).enqueue(callBack);
    }
    public void deleteTask(int id, Callback<TaskResponse> callBack) {
        taskAPI.deleteTask(id).enqueue(callBack);
    }
    public void createTask(CreateTaskRequest request, Callback<TaskResponse> callBack) {
        taskAPI.createTask(request).enqueue(callBack);
    }
    public void updateTask(int id, UpdateTaskRequest request, Callback<TaskResponse> callBack) {
        taskAPI.updateTask(id, request).enqueue(callBack);
    }
    public void uploadConfirmationImage(byte[] imageBytes, String fileName, Callback<TaskResponse> callback) {
        RequestBody requestFile = RequestBody.create(MediaType.parse("image/jpeg"), imageBytes);
        MultipartBody.Part body = MultipartBody.Part.createFormData("file", fileName, requestFile);
        taskAPI.uploadConfirmation(body).enqueue(callback);
    }
}
