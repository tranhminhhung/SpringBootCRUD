package com.crud.srpringbootcrud.exception;

public class EmptyOrNullException extends RuntimeException{
    public EmptyOrNullException(String message){
        super(message);
    }
}
