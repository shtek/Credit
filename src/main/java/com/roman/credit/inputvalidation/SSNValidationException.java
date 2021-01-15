package com.roman.credit.inputvalidation;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

@ResponseStatus(value= UNPROCESSABLE_ENTITY, reason="SSN is Invalid")
public class SSNValidationException extends IllegalArgumentException{

    public SSNValidationException(String message) {
          super(message);

    }
    public SSNValidationException(String message,Throwable cause) {
        super(message,cause);

    }

}
