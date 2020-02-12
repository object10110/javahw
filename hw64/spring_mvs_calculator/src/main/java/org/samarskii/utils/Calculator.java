package org.samarskii.utils;

import java.math.BigDecimal;

public final class Calculator {
    public static String Calc(BigDecimal a, BigDecimal b, String op)
    {
        switch (op) {
            case "+": return a.add(b).toString();
            case "-": return  a.subtract(b).toString();
            case "*": return  a.multiply(b).toString();
            case "/":{
                if(b.floatValue() == 0) {
                    return "error";
                }
                return a.divide(b).toString();
            }
            default: return "error";
        }
    }
}
