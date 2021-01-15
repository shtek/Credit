package com.roman.credit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.roman.credit.Constants.MAX_HISTORY_DAYS;
import static com.roman.credit.Constants.MIN_CREDIT_SCORE;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CreditDecisionEngineServiceImpl implements CreditDecisionEngineService{
    @Autowired
    private LoanDecision loanDecision;
    @Autowired
    private HistoryOfSearchesService historyOfSearchesService;
    @Autowired
    private CreditScoreService creditScoreService;

    @Override
    public LoanDecision getDecision(String ssn, double annualIncome) {
        //another option would be to use Exception and stop the flow
        //if(historyOfSearchesService.wasRecentlySearched(ssn))
        //    throw new DuplicateApplicationException("User with this SSN" + ssn + "already applied in the past 30 days");
        if(historyOfSearchesService.wasRecentlySearched(ssn))
        {
            loanDecision.setMessage("User with this SSN " + ssn + " already applied in the past " + MAX_HISTORY_DAYS + " days");

        }
        else{
            historyOfSearchesService.updateHistory(ssn);
            loanDecision =  makeDecision(creditScoreService.getCreditScore(ssn),annualIncome);
        }
        return loanDecision;

    }
    private LoanDecision makeDecision(int creditScore, double annualIncome){
        loanDecision.setCreditScore(creditScore);
        if(creditScore > MIN_CREDIT_SCORE) {
            loanDecision.setSanctionedLoanAmmount(annualIncome / 2);
            loanDecision.setMessage("You have being granted loan");
        }
        else
            loanDecision.setMessage("You score is too low for loan");
        return loanDecision;
    }
}
