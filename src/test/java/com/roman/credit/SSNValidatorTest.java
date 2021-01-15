package com.roman.credit;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import com.roman.credit.inputvalidation.SSNValidator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SSNValidatorTest {
    @Autowired
    private SSNValidator validator;
    @Test
    public void validSSN() {
        String str1 = "856-45-6789";
        assertTrue(validator.isValidSSN(str1));
    }
    @Test
    public void invalidSSN() {
        String str1 = "000-45-6789";
        assertFalse(validator.isValidSSN(str1));
    }
}
