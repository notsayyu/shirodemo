package com.example.shirodemo.config;

public class RateLimitException extends  Exception {

    private String errorMessage;

    public RateLimitException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage(){
        return errorMessage;
    }
}
