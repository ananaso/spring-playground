package com.example.SpringWord;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/words")
public class WordCounterController {
    @Autowired
    private WordCounter wordCounter;

    @PostMapping("/count")
    public String getWordCount(@RequestBody String str) {
        return this.wordCounter.count(str).toString();
    }
//
//    private String prettifyJSONString(String str) {
//        Gson gson = new GsonBuilder().setPrettyPrinting().create();
//        return gson.toJson(str);
//    }
}
