package com.example.jetbill.msvusers.exceptions;

public class UserServiceLogicException extends Exception{
    public UserServiceLogicException() {
        super("Something went wrong. Please try again later!");
    }
}
