package com.roman.credit;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.TOO_MANY_REQUESTS;
//@ResponseStatus(value= TOO_MANY_REQUESTS, reason="Multiple application")
@Deprecated
public class DuplicateApplicationException extends RuntimeException {
/*    public DuplicateApplicationException(String message) {
        super(message);
    }

    public DuplicateApplicationException(String message, Throwable cause)
    {
        super(message, cause);
    }

 */
}



