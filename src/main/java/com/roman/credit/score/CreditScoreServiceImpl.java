package com.roman.credit.score;

import com.roman.credit.score.CreditScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CreditScoreServiceImpl implements CreditScoreService {
    public static final String MOCK_SERVICE_URL = "http://l7q9g.mocklab.io/json/";
    @Autowired
    RestTemplate restTemplate;

    @Override
    public int getCreditScore(String ssn) {
       Integer creditScore = restTemplate.getForObject(
                MOCK_SERVICE_URL + ssn, Integer.class);
           return creditScore.intValue();
    }
}
