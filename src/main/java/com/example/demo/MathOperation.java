package com.example.demo;

public class MathOperation {
    private Integer x;
    private Integer y;
    private String op;
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
            case "add":         result = this.x + this.y;
                                break;
            case "multiply":    result = this.x * this.y;
                                break;
            case "subtract":    result = this.x - this.y;
                                break;
            case "divide":      result = this.x / this.y;
                                break;
            default:            result = 0;
                                break;
        }
        return result;
    }

    public String getX() {
        return x.toString();
    }

    public void setX(String x) {
        this.x = Integer.parseInt(x);
    }

    public String getY() {
        return y.toString();
    }

    public void setY(String y) {
        this.y = Integer.parseInt(y);
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
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
