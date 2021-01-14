package com.roman.credit;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE;

@ResponseStatus(value= REQUESTED_RANGE_NOT_SATISFIABLE, reason="SSN is Invalid")
public class SSNValidationException extends RuntimeException{
    public SSNValidationException(String message) {
          super(message);

    }

}
