package com.roman.credit;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoanDecisionTest {
    LoanDecision loanDecision= new LoanDecision(30,50);


   @Test
    void getMessage() {
        loanDecision.setMessage("Test");
        org.junit.jupiter.api.Assertions.assertEquals(loanDecision.getMessage(),"Test");
    }

    @Test
    void setMessage() {
        loanDecision.setMessage("Test");
        org.junit.jupiter.api.Assertions.assertEquals(loanDecision.getMessage(),"Test");

    }

    @Test
    void getCreditScore() {
        org.junit.jupiter.api.Assertions.assertEquals(loanDecision.getCreditScore(),30);

    }
   @Test
    void getSanctionedLoanAmmount() {
        org.junit.jupiter.api.Assertions.assertEquals(loanDecision.getSanctionedLoanAmmount(),50);

    }


    @Test
    void setCreditScore() {
        loanDecision.setCreditScore(40);
        org.junit.jupiter.api.Assertions.assertEquals(loanDecision.getCreditScore(),40);

    }


    @Test
    void setSanctionedLoanAmmount() {
        loanDecision.setSanctionedLoanAmmount(110.0);
        org.junit.jupiter.api.Assertions.assertEquals(loanDecision.getSanctionedLoanAmmount(),110.0);

    }
}