package com.ir;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InvertedIndex {
    private Map<String, Map<Integer, Integer>> invertedIndex;

    public InvertedIndex() {
        invertedIndex = new HashMap<>();
    }

    public void addDocument(int docId, List<String> tokens) {
        for (String token : tokens) {
            invertedIndex.computeIfAbsent(token, k -> new HashMap<>());
            Map<Integer, Integer> docFreqMap = invertedIndex.get(token);
            docFreqMap.merge(docId, 1, Integer::sum);
        }
    }

    public void saveToFile(String filePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Map.Entry<String, Map<Integer, Integer>> entry : invertedIndex.entrySet()) {
                writer.write(entry.getKey() + ": ");
                for (Map.Entry<Integer, Integer> docEntry : entry.getValue().entrySet()) {
                    writer.write(docEntry.getKey() + ": " + docEntry.getValue() + "; ");
                }
                writer.newLine();
            }
        }
    }

    public Map<Integer, Integer> search(String token) {
        return invertedIndex.getOrDefault(token, Collections.emptyMap());
    }
}