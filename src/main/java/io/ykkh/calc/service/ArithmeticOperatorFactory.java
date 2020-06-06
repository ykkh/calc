package io.ykkh.calc.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ArithmeticOperatorFactory {
    static Map<String, ArithmeticOperation> operationMap = new HashMap<>();
    static {
        operationMap.put("add", new Addition());
        operationMap.put("div", new Division());
        operationMap.put("sub", new Subtraction());
        operationMap.put("mul", new Multiplication());
    }
 
    public static Optional<ArithmeticOperation> getOperation(String operator) {
        return Optional.ofNullable(operationMap.get(operator));
    }
    
}