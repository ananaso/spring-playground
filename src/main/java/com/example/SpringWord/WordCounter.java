package com.example.SpringWord;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;
import java.util.Map;

public class WordCounter {
    private Map<String, Integer> wordCount = new HashMap<>();

    public Map<String, Integer> count(String str) {
        Map<String, Integer> wordCountMap = new HashMap<>();
        for (String word : str.replaceAll("[^a-zA-Z ]", "").toLowerCase().split(" ")) {
            if(wordCountMap.containsKey(word)) {
                Integer count = wordCountMap.get(word) + 1;
                wordCountMap.put(word, count);
            } else {
                wordCountMap.put(word, 1);
            }
        }
        this.wordCount = wordCountMap;
        return wordCountMap;
    }

//    @Override
//    public String toString() {
//        Gson gson = new GsonBuilder().setPrettyPrinting().create();
//        return gson.toJson(this.wordCount);
//    }
}
