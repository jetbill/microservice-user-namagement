package com.example.jetbill.msvusers.exceptions;

import lombok.Getter;

@Getter
public enum ExceptionMessageResponse {
    USER_NOT_FOUND("User not found with id");

    private final String message;

    ExceptionMessageResponse(String message){
        this.message = message;

    }



}
