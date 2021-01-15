package com.roman.credit;

import com.roman.credit.history.HistoryOfSearchesService;
import com.roman.credit.history.HistoryOfSearchesServiceImpl;
import com.roman.credit.inputvalidation.SSNValidator;
import org.junit.jupiter.api.Test;
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
   @Autowired
    private CreditDecisionEngineServiceImpl creditDecisionEngineService;
    private static final String SSN ="123";
   @Test
    public void getDecisionDuplicateApplication() throws NoSuchFieldException {

      FieldSetter.setField(creditDecisionEngineService, creditDecisionEngineService.getClass().getDeclaredField("historyOfSearchesService"), historyOfSearchesService);
      when(historyOfSearchesService.wasRecentlySearched(SSN)).thenReturn(true);
      LoanDecision loanDecision = creditDecisionEngineService.getDecision(SSN,30);
      Assert.hasText (loanDecision.getMessage(),"User with this SSN " + SSN + " already applied in the past " + MAX_HISTORY_DAYS + " days");
      Mockito.verify(historyOfSearchesService).wasRecentlySearched(SSN);




   }

}
