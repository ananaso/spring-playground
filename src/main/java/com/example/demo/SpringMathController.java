package com.example.demo;

import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/math")
public class SpringMathController {
    @GetMapping("/pi")
    public String getPi() {
        return "3.141592653589793";
    }

    @GetMapping("/calculate")
    public String calculate(@RequestParam Map<String, String> paramMap) {
        Integer x = Integer.parseInt(paramMap.get("x"));
        Integer y = Integer.parseInt(paramMap.get("y"));
        String op = paramMap.get("operation");

        MathOperation mathOp = new MathOperation(x, y, op);
        return mathOp.toString();
    }

    @PostMapping("/sum")
    public String sum(@RequestParam int[] n) {
        return MathOperation.getSumString(n);
    }

    @RequestMapping("/volume/{length}/{width}/{height}")
    public String volume(@PathVariable Map<String, String> pathVariables) {
        return MathOperation.getVolumeString(pathVariables);
    }

    @PostMapping("/area")
    public String area(@RequestParam Map<String, String> body) {
        return MathOperation.getArea(body);
    }
}
