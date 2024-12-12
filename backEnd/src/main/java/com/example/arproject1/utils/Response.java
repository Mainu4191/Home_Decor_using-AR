package com.example.arproject1.utils;

public class Response {

    private String message;
    private boolean status;
    private String userId;

    public Response(String message, boolean status) {
        this.message = message;
        this.status = status;
    }

    public Response(String message, boolean status, String userId) {
        this.message = message;
        this.status = status;
        this.userId = userId;
    }

    public Response() {
    }

    public String getMessage() {
        return message;
    }

    public boolean isStatus() {
        return status;
    }

    public String getUserId(){
        return userId;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
