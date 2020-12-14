package com.example.SpringWord;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WordCounterTest {
    @Test
    public void testWordCounter() {
        String testStr = "Can a string that has multiple words turn into a map with multiple words?";
        WordCounter wordCounter = new WordCounter();

        Map<String, Integer> result = wordCounter.count(testStr);
        Map<String, Integer> expected = new HashMap<>();
        expected.put("can", 1);
        expected.put("a", 2);
        expected.put("string", 1);
        expected.put("that", 1);
        expected.put("has", 1);
        expected.put("multiple", 2);
        expected.put("words", 2);
        expected.put("turn", 1);
        expected.put("into", 1);
        expected.put("map", 1);
        expected.put("with", 1);

        assertEquals(expected, result);
    }
}
