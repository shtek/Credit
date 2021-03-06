package com.roman.credit.history;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.roman.credit.Constants.MAX_HISTORY_DAYS;

@Service
public class HistoryOfSearchesServiceImpl implements HistoryOfSearchesService {
    private Map<String, LocalDate> searchesHistory = new ConcurrentHashMap<>();

    @Override
    public boolean wasRecentlySearched(String ssn) {
        LocalDate lastAccess = searchesHistory.get(ssn);
        if((lastAccess != null ) && ( lastAccess.isAfter(LocalDate.now().minusDays(MAX_HISTORY_DAYS))))
            return true;
        else
            return false;
    }

    @Override
    public void updateHistory(String ssn) {
        searchesHistory.put(ssn,LocalDate.now());
    }
}
