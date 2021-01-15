package com.roman.credit.history;
import org.springframework.stereotype.Service;

@Service
public interface HistoryOfSearchesService {
    boolean wasRecentlySearched(String ssn);
    void updateHistory(String ssn);

}
