package com.roman.credit;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public interface CreditDecisionEngineService {
    public LoanDecision getDecision(String ssn, double annualIncome);
}
