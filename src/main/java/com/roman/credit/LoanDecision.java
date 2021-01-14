package com.roman.credit;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class LoanDecision {
    private int creditScore;
    private double sanctionedLoanAmmount;
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(int creditScore) {
        this.creditScore = creditScore;
    }

    public double getSanctionedLoanAmmount() {
        return sanctionedLoanAmmount;
    }

    public void setSanctionedLoanAmmount(double sanctionedLoanAmmount) {
        this.sanctionedLoanAmmount = sanctionedLoanAmmount;
    }

    public LoanDecision(int creditScore, double sanctionedLoanAmmount){
        this.creditScore= creditScore;
        this.sanctionedLoanAmmount= sanctionedLoanAmmount;
    }
    public LoanDecision(){

    }
}
