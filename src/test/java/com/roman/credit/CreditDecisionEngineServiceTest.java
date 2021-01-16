package com.roman.credit;

import com.roman.credit.history.HistoryOfSearchesService;
import static org.mockito.Mockito.never;
import com.roman.credit.history.HistoryOfSearchesServiceImpl;
import com.roman.credit.inputvalidation.SSNValidator;
import com.roman.credit.score.CreditScoreService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.FieldSetter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import static com.roman.credit.Constants.MAX_HISTORY_DAYS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest

public class CreditDecisionEngineServiceTest {
    @Mock
    HistoryOfSearchesService historyOfSearchesService ;
    @Mock
    private CreditScoreService creditScoreService;
   @Autowired
    private CreditDecisionEngineService creditDecisionEngineService;
    private static final String SSN ="123";

   @Test
    public void getDecisionDuplicateApplication() throws NoSuchFieldException {

      FieldSetter.setField(creditDecisionEngineService, creditDecisionEngineService.getClass().getDeclaredField("historyOfSearchesService"), historyOfSearchesService);
      when(historyOfSearchesService.wasRecentlySearched(SSN)).thenReturn(true);
      LoanDecision loanDecision = creditDecisionEngineService.getDecision(SSN,30);
      Assert.hasText (loanDecision.getMessage(),"User with this SSN " + SSN + " already applied in the past " + MAX_HISTORY_DAYS + " days");
      Mockito.verify(historyOfSearchesService).wasRecentlySearched(SSN);
 }
    @Test
    public void getDecisionCreditScoreIsAboveThreshhold() throws NoSuchFieldException
    {
        FieldSetter.setField(creditDecisionEngineService, creditDecisionEngineService.getClass().getDeclaredField("historyOfSearchesService"), historyOfSearchesService);
        when(historyOfSearchesService.wasRecentlySearched(SSN)).thenReturn(false);
        FieldSetter.setField(creditDecisionEngineService, creditDecisionEngineService.getClass().getDeclaredField("creditScoreService"), creditScoreService);
        when(creditScoreService.getCreditScore(SSN)).thenReturn(Constants.CREDIT_SCORE_THRESHHOLD +1);
        LoanDecision loanDecision = creditDecisionEngineService.getDecision(SSN,30);
        Assert.hasText (loanDecision.getMessage(),"You have being granted loan");
        assertThat(15.0).isEqualTo(loanDecision.getSanctionedLoanAmmount());
        Mockito.verify(historyOfSearchesService).wasRecentlySearched(SSN);
        Mockito.verify(historyOfSearchesService).updateHistory(SSN);
        Mockito.verify(creditScoreService).getCreditScore(SSN);


    }

    @Test
    public void getDecisionCreditScoreIsBelowThreshhold() throws NoSuchFieldException
    {
        FieldSetter.setField(creditDecisionEngineService, creditDecisionEngineService.getClass().getDeclaredField("historyOfSearchesService"), historyOfSearchesService);
        when(historyOfSearchesService.wasRecentlySearched(SSN)).thenReturn(false);
        FieldSetter.setField(creditDecisionEngineService, creditDecisionEngineService.getClass().getDeclaredField("creditScoreService"), creditScoreService);
        when(creditScoreService.getCreditScore(SSN)).thenReturn(Constants.CREDIT_SCORE_THRESHHOLD - 1);
        LoanDecision loanDecision = creditDecisionEngineService.getDecision(SSN,30);
        Assert.hasText (loanDecision.getMessage(),"You score is too low for loan");
        Mockito.verify(historyOfSearchesService).wasRecentlySearched(SSN);
        Mockito.verify(historyOfSearchesService,Mockito.times(1)).updateHistory(SSN);
        Mockito.verify(creditScoreService).getCreditScore(SSN);


    }
}
