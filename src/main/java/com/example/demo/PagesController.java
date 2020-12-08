package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PagesController {
    @GetMapping("/hello")
    public String sayHello() {
        return "Hello world";
    }

    @PostMapping("/comments")
    public String postComment(String author, String content) {
        return String.format("%s said %s!", author, content);
    }
}
