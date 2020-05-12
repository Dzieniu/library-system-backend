package com.github.dzieniu.libsysbe.exception;

public class OperationNotSupportedException extends RuntimeException{

    public OperationNotSupportedException(String message) {
        super(message);
    }
}