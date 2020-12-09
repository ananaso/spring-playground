package com.example.demo;

import java.util.*;

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

    public static String getArea(Map<String, String> body) {
        String type = body.get("type");
        double area = 0;
        StringBuilder outStr = new StringBuilder("Area of a ");
        Locale locale = Locale.forLanguageTag("en-US");
        if (type.equals("circle")) {
            int radius = Integer.parseInt(body.get("radius"));
            area = Math.PI * Math.pow(radius, 2);

            String strEnd = String.format(locale, "circle with a radius of %d is %.5f", radius, area);
            outStr.append(strEnd);
        } else if (type.equals("rectangle")) {
            int width = Integer.parseInt(body.get("width"));
            int height = Integer.parseInt(body.get("height"));
            area = Math.multiplyExact(width, height);

            String strEnd = String.format(locale, "%dx%d rectangle is %d", width, height, area);
            outStr.append(strEnd);
        }
        return outStr.toString();
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
