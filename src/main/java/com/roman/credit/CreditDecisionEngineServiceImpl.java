package com.roman.credit;

import com.roman.credit.history.HistoryOfSearchesService;
import com.roman.credit.score.CreditScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import static com.roman.credit.Constants.MAX_HISTORY_DAYS;
import static com.roman.credit.Constants.CREDIT_SCORE_THRESHHOLD;

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
        if(creditScore > CREDIT_SCORE_THRESHHOLD) {
            loanDecision.setSanctionedLoanAmmount(annualIncome / 2);
            loanDecision.setMessage("You have being granted loan");
        }
        else
            loanDecision.setMessage("You score is too low for loan");
        return loanDecision;
    }
}
