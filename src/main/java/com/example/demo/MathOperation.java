package com.example.demo;

import java.util.LinkedList;
import java.util.List;

public class MathOperation {
    private Integer x = 0;
    private Integer y = 0;
    private String op = "add";
    private Integer result = 0;
    private List<String> values = new LinkedList<>();

    public MathOperation(Integer x, Integer y, String operation) {
        this.x = x;
        this.y = y;
        this.op = operation != null ? operation : "add";
        this.result = this.calculateResult();
    }

    public MathOperation(List<String> n) {
        this.values = n;
    }

    private Integer calculateResult() {
        int result;
        switch (this.op) {
            case "add":         result = Math.addExact(this.x, this.y);
                                break;
            case "multiply":    result = Math.multiplyExact(this.x, this.y);
                                break;
            case "subtract":    result = Math.subtractExact(this.x, this.y);
                                break;
            case "divide":      result = Math.floorDiv(this.x, this.y);
                                break;
            default:            result = 0;
                                break;
        }
        return result;
    }

    private String getOpSymbol() {
        String opSymbol;
        switch (this.op) {
            case "add":         opSymbol = "+";
                                break;
            case "multiply":    opSymbol = "*";
                                break;
            case "subtract":    opSymbol = "-";
                                break;
            case "divide":      opSymbol = "/";
                                break;
            default:            opSymbol = "bad opCode";
                                break;
        }
        return opSymbol;
    }

    @Override
    public String toString() {
        return String.format("%d %s %d = %d", this.x, this.getOpSymbol(), this.y, this.result);
    }
}
