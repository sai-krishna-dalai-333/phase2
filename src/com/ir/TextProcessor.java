package com.ir;

import java.util.*;
import java.util.regex.*;

public class TextProcessor {
    private Set<String> stopWords;
    private Porter stemmer;

    public TextProcessor(Set<String> stopWords, Porter stemmer) {
        this.stopWords = stopWords;
        this.stemmer = stemmer;
    }

    public List<String> process(String content) {
        List<String> tokens = new ArrayList<>();
        Pattern pattern = Pattern.compile("\\w+");
        Matcher matcher = pattern.matcher(content.toLowerCase());

        while (matcher.find()) {
            String token = matcher.group();
            if (!stopWords.contains(token)) {
                String stemmedToken = stemmer.stripAffixes(token);
                tokens.add(stemmedToken);
            }
        }
        return tokens;
    }
}