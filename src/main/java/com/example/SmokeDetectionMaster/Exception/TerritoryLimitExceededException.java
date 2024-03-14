package com.example.SmokeDetectionMaster.Exception;




/**
 * @author wsh
 */
public class TerritoryLimitExceededException extends Exception {
    private String message; //异常信息

    public TerritoryLimitExceededException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
