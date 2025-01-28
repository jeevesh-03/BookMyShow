package com.sampleProject.bookMyShowApp.exceptions;

public class WrongArgumentException extends RuntimeException{
    public WrongArgumentException(String message){
        super(message);
    }
}
