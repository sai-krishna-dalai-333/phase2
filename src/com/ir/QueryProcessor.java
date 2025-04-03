package com.ir;
import java.util.Map;

public class QueryProcessor {
    private InvertedIndex invertedIndex;

    public QueryProcessor(InvertedIndex invertedIndex) {
        this.invertedIndex = invertedIndex;
    }

    public Map<Integer, Integer> query(String searchTerm) {
        return invertedIndex.search(searchTerm);
    }
}