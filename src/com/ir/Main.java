package com.ir;
import java.io.*;
import java.nio.file.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Set<String> stopWords = loadStopWords("./data/input/stopwordlist.txt");
        Porter stemmer = new Porter();
        TextProcessor processor = new TextProcessor(stopWords, stemmer);
        DocIndex docIndex = new DocIndex();
        InvertedIndex invertedIndex = new InvertedIndex();
        File folder = new File("./data/input");
        if (!folder.exists() || !folder.isDirectory()) {
            System.err.println("Folder ./data/input does not exist or is not a directory.");
            return;
        }

        int docID = 1;
        for (File file : folder.listFiles()) {
            if (file.isFile()) {
                String content = new String(Files.readAllBytes(file.toPath()));
                List<String> tokens = processor.process(parseTextContent(content));
                docIndex.addDocument(docID, tokens);
                invertedIndex.addDocument(docID, tokens);
                docID++;
            }
        }

        // Save indices to files
        docIndex.saveToFile("./data/output/forward_index.txt");
        invertedIndex.saveToFile("./data/output/inverted_index.txt");

        // Initialize query processor
        QueryProcessor queryProcessor = new QueryProcessor(invertedIndex);

        // Example search
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a word to search: ");
        String searchTerm = scanner.nextLine();
        scanner.close();
        Map<Integer, Integer> results = queryProcessor.query(searchTerm);
        System.out.println("Documents contains the word '" + searchTerm + "': " + results);
    }

    private static Set<String> loadStopWords(String filePath) throws IOException {
        Set<String> stopWords = new HashSet<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                stopWords.add(line.trim());
            }
        }
        return stopWords;
    }

    private static String parseTextContent(String content) {
        int start = content.indexOf("<TEXT>");
        int end = content.indexOf("</TEXT>");
        if (start != -1 && end != -1) {
            return content.substring(start + 6, end).trim();
        }
        return "";
    }
}