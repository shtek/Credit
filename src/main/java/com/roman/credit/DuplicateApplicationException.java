package com.roman.credit;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
@ResponseStatus(value= BAD_REQUEST, reason="Multiple application")
public class DuplicateApplicationException extends RuntimeException {
    public DuplicateApplicationException(String message){
        super(message);
    }
}




