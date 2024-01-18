package com.bilgeadam.exception;

import lombok.Getter;



@Getter
public class EmployeeManagerException extends RuntimeException{

    private final ErrorType errorType;

    public EmployeeManagerException(ErrorType errorType, String customMessage){
        super(customMessage);
        this.errorType = errorType;
    }
    public EmployeeManagerException(ErrorType errorType){
        super(errorType.getMessage());
        this.errorType = errorType;
    }
}
