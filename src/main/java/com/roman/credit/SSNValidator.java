package com.roman.credit;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public interface SSNValidator {
    static final String REGEX = "^(?!666|000|9\\d{2})\\d{3}-(?!00)\\d{2}-(?!0{4})\\d{4}$";

    public  boolean isValidSSN(String str);
}
