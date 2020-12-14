package com.example.SpringWord;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class WordCounter {
    public WordCounter wordCounter() {
        return new WordCounter();
    }

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
        return wordCountMap;
    }
}
