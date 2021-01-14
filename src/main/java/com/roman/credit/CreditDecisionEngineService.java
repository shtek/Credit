package com.roman.credit;

import org.springframework.stereotype.Service;

@Service
public interface CreditDecisionEngineService {
    public LoanDecision getDecision(String ssn, double annualIncome);
}
