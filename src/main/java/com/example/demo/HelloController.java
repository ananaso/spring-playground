package com.example.demo;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/app")
public class HelloController {
    @GetMapping("/")
    public String helloWorld() {
        return "Hello from Spring!";
    }

    @GetMapping("/tasks")
    public String getTasks() {
        return "These are tasks";
    }

    @PostMapping("/tasks")
    public String createTask() {
        return "You just POSTed to /tasks";
    }

}