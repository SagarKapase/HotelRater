package com.lcwd.user.service.exception;

public class ResourceNotFoundEXception extends RuntimeException{

    ResourceNotFoundEXception(){
        super("Resource not found on server");
    }
    public ResourceNotFoundEXception(String message){
        super(message);
    }
}
