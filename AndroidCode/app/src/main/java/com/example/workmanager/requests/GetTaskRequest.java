package com.example.workmanager.requests;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class GetTaskRequest implements Serializable {
    @SerializedName("userId")
    int userId;
    @SerializedName("startTime")
    String startTime;
    @SerializedName("endTime")
    String endTime;
    @SerializedName("handlerId")
    int handlerId;
    @SerializedName("statusId")
    int statusId;

    public GetTaskRequest(){

    }

    public GetTaskRequest(int userId, String startTime, String endTime, int handlerId, int statusId) {
        this.userId = userId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.handlerId = handlerId;
        this.statusId = statusId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getHandlerId() {
        return handlerId;
    }

    public void setHandlerId(int handlerId) {
        this.handlerId = handlerId;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }
}
