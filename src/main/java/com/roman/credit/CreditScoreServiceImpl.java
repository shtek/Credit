package com.roman.credit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CreditScoreServiceImpl implements  CreditScoreService{
    @Autowired
    RestTemplate restTemplate;

    @Override
    public int getCreditScore(String ssn) {
       Integer creditScore = restTemplate.getForObject(
                "http://l7q9g.mocklab.io/json/"+ ssn, Integer.class);
           return creditScore.intValue();
    }
}
