package com.example.SmokeDetectionMaster.Bean.Utils;

import com.example.SmokeDetectionMaster.Bean.Territory.Territory;

import java.io.Serializable;
import java.util.List;

/**
 * @author wsh
 */
public class Result<T> implements Serializable {
    private boolean success;
    private String message;
    private T data;
    public Result(boolean success, String message) {
        super();
        this.success=success;
        this.message = message;
    }
    public Result(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public Result(boolean success, ResponseMessage responseMessage, T data) {
        this.success = success;
        this.message = String.valueOf(responseMessage);
        this.data = data;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public boolean isSuccess() {
        return success;
    }
    public void setSuccess(boolean success) {
        this.success = success;
    }
    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }
}