package com.ir;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DocIndex {
    private Map<Integer, Map<String, Integer>> docIndex;

    public DocIndex() {
        docIndex = new HashMap<>();
    }

    public void addDocument(int docId, List<String> tokens) {
        Map<String, Integer> frequencyMap = new HashMap<>();
        for (String token : tokens) {
            frequencyMap.put(token, frequencyMap.getOrDefault(token, 0) + 1);
        }
        docIndex.put(docId, frequencyMap);
    }

    public void saveToFile(String filePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Map.Entry<Integer, Map<String, Integer>> entry : docIndex.entrySet()) {
                writer.write(entry.getKey() + ": ");
                for (Map.Entry<String, Integer> termEntry : entry.getValue().entrySet()) {
                    writer.write(termEntry.getKey() + ": " + termEntry.getValue() + "; ");
                }
                writer.newLine();
            }
        }
    }
}