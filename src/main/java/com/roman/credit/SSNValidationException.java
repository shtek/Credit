package com.roman.credit;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ResponseStatus(value= BAD_REQUEST, reason="SSN is Invalid")
public class SSNValidationException extends RuntimeException{
    public SSNValidationException(String message) {
          super(message);

    }

}
