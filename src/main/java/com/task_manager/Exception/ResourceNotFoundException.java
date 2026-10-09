package com.task_manager.Exception;

public  class ResourceNotFoundException extends RuntimeException{


    public ResourceNotFoundException(String message){
        super(message);
    }
}