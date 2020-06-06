package io.ykkh.calc.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import io.ykkh.calc.common.CalcAppConstants;

public class ArithmeticOperatorFactory {
    static Map<String, ArithmeticOperation> operationMap = new HashMap<>();
    static {
        operationMap.put(CalcAppConstants.ADDITION, new Addition());
        operationMap.put(CalcAppConstants.DIVISION, new Division());
        operationMap.put(CalcAppConstants.SUBSTRACTION, new Subtraction());
        operationMap.put(CalcAppConstants.MULTIPLICATION, new Multiplication());
    }
 
    public static Optional<ArithmeticOperation> getOperation(String operator) {
        return Optional.ofNullable(operationMap.get(operator));
    }
    
}