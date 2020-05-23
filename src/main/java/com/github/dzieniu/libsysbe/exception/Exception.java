package com.github.dzieniu.libsysbe.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class Exception extends RuntimeException {
    public Exception(String message) {
        super(message);
    }
}
