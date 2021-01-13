package com.roman.credit;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
public class CreditDecisionEngineController {
    @RequestMapping(value="/ssnNumber/{ssnNumber}/loanAmmount/{loanAmmount}")
    public @ResponseBody String getLoanDecesion(@PathVariable("ssnNumber") String ssnNumber,@PathVariable("loanAmmount") double loanAmmount,@RequestParam("annualIncome") double annualIncome){

        return "str value" + ssnNumber + loanAmmount+ annualIncome;
    }
}
