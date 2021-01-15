package com.roman.credit.inputvalidation;
import org.springframework.stereotype.Component;

import java.util.regex.*;
@Component
public class SSNValidatorImpl implements SSNValidator {
    // Regex to check SSN (Social Security Number).

    public  boolean isValidSSN(String str) {
        Pattern p = Pattern.compile(REGEX);
        if (str == null) {
            return false;
        }
        Matcher m = p.matcher(str);
        return m.matches();
    }
}
