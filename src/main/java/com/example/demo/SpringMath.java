package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/math")
public class SpringMath {
    @GetMapping("/pi")
    public String getPi() {
        return "3.141592653589793";
    }

    @PostMapping("/calculate")
    public String calculate(MathOperation mathOp) {
        return mathOp.toString();
    }
}
