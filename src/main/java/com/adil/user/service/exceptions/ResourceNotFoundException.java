package com.adil.user.service.exceptions;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(){
        super("Resource Not found on server !!");
    }

    public ResourceNotFoundException(String msg) {
        super(msg);
    }
}
