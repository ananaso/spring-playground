package com.example.demo;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MathOperation {
    private Integer x = 0;
    private Integer y = 0;
    private String op = "add";
    private Integer result = 0;

    public MathOperation(Integer x, Integer y, String operation) {
        this.x = x;
        this.y = y;
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

    public static String getVolumeString(Map<String, String> sideLengths) {
        int length = Integer.parseInt(sideLengths.get("length"));
        int width = Integer.parseInt(sideLengths.get("width"));
        int height = Integer.parseInt(sideLengths.get("height"));
        int volume = length * width * height;
        return String.format("The volume of a %dx%dx%d rectangle is %d", length, width, height, volume);
    }

    public static String getSumString(int[] values) {
        int sum = 0;
        StringBuilder valStr = new StringBuilder();
        for (int i = 0; i < values.length; i++) {
            int val = values[i];
            sum += val;
            valStr.append(val);
            if (i == values.length - 1) {
                valStr.append(" = ");
            } else {
                valStr.append(" + ");
            }
        }
        return String.format("%s%d", valStr.toString(), sum);
    }
}
