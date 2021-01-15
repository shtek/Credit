package com.roman.credit.score;

import org.springframework.stereotype.Service;

@Service
public interface CreditScoreService {
    int getCreditScore(String ssn);
}
