package com.roman.credit;
import com.roman.credit.inputvalidation.SSNValidator;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest
public class SmokeTest {
    @Autowired
    private SSNValidator validator;

    @Autowired
    private CreditDecisionEngineController controller;
   @Test
    //assert that context is loaded and isnot null
    public void contextLoads() {
        assertThat(controller).isNotNull();
    }
   @Test
     public void SSNValidatorLoads() {
      assertThat(validator).isNotNull();

    }

}
