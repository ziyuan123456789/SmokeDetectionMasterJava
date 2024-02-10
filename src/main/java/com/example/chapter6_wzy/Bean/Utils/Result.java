package com.example.chapter6_wzy.Bean.Utils;

import java.io.Serializable;

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