package com.roman.credit;
import com.roman.credit.inputvalidation.SSNValidationException;
import com.roman.credit.inputvalidation.SSNValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CreditDecisionEngineController {
    @Autowired
    private SSNValidator validator;
    @Autowired
    private CreditDecisionEngineService creditDecisionEngineService;

    @RequestMapping(value="/ssnNumber/{ssnNumber}/loanAmmount/{loanAmmount}")
    public @ResponseBody LoanDecision getLoanDecision(@PathVariable("ssnNumber") String ssnNumber, @PathVariable("loanAmmount") double loanAmmount, @RequestParam("annualIncome") double annualIncome){
       if(!validator.isValidSSN(ssnNumber))
            throw new SSNValidationException("Invalid SSN provided");


       return creditDecisionEngineService.getDecision(ssnNumber,annualIncome);
    }

}
