package com.bilgeadam.exception;

import lombok.Getter;

@Getter
public class ManagerManagerException extends RuntimeException{

    private final ErrorType errorType;

    public ManagerManagerException(ErrorType errorType, String customMessage){
        super(customMessage);
        this.errorType = errorType;
    }
    public ManagerManagerException(ErrorType errorType){
        super(errorType.getMessage());
        this.errorType = errorType;
    }
}
