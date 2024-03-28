package com.example.jetbill.msvusers.exceptions;

import lombok.Getter;

@Getter
public enum ExceptionMessageResponse {
    USER_EXIST_WITH_EMAIL("Registration failed: User already exists with email "),
    USER_EXIST_WITH_USERNAME("Registration failed: User already exists with username "),
    NEW_USER_CREATED("New user  has been successfully created!"),
    USER_UPDATE_SUCCESSFULLY("User  updated successfully!"),
    USER_DELETE("User  deleted successfully!"),
    USER_NOT_FOUND("User not found with id");


    private final String message;

    ExceptionMessageResponse(String message){
        this.message = message;

    }



}
