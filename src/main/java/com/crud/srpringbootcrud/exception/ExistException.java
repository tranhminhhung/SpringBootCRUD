package com.crud.srpringbootcrud.exception;

public class ExistException extends RuntimeException{
    public ExistException(String message){
        super(message);
    }
}
