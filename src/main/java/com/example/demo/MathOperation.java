package com.example.demo;

public class MathOperation {
    private final Integer x;
    private final Integer y;
    private final String op;
    private final Integer result;

    public MathOperation(String x, String y, String operation) {
        this.x = Integer.parseInt(x);
        this.y = Integer.parseInt(y);
        this.op = operation != null ? operation : "add";
        this.result = this.calculateResult();
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
