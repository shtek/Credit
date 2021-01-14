package com.roman.credit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class CreditDecisionEngineServiceImpl implements CreditDecisionEngineService{
    //factor out the searchesHistory into a service , that can be then substituted
    //private Map<String, LocalDate> searchesHistory = new ConcurrentHashMap<>();
    @Autowired
    private LoanDecision loanDecision;
    @Autowired
    private HistoryOfSearchesService historyOfSearchesService;


    @Override
    public LoanDecision getDecision(String ssn, double annualIncome) {
     //todo: see if not recently searched.use recentlySearched function
        if(historyOfSearchesService.wasRecentlySearched(ssn))
            throw new DuplicateApplicationException("User with this SSN" + ssn + "already applied in the past 30 days");
        //then check the external service for score
        //then run the logoc to decied the ammount
       historyOfSearchesService.updateHistory(ssn);

       loanDecision.setCreditScore(1000);
       loanDecision.setSanctionedLoanAmmount(5);
       return loanDecision;
    }
}
