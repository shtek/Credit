package com.roman.credit;

import org.springframework.stereotype.Service;

@Service
public interface CreditScoreService {
    int getCreditScore(String ssn);
}
