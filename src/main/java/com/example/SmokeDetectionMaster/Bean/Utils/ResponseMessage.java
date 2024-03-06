package com.example.SmokeDetectionMaster.Bean.Utils;

/**
 * @author ziyuan
 * @since 2024.02
 */
public enum ResponseMessage {
    SUCCESS("Operation successful"),
    FAILURE("Operation failed"),
    ERROR("An error occurred"),
    NOT_FOUND("Resource not found");

    private final String message;

    ResponseMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
