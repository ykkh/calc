package io.ykkh.calc.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import io.ykkh.calc.common.AppConstants;

public class ArithmeticOperatorFactory {
    static Map<String, ArithmeticOperation> operationMap = new HashMap<>();
    static {
        operationMap.put(AppConstants.ADDITION, new Addition());
        operationMap.put(AppConstants.DIVISION, new Division());
        operationMap.put(AppConstants.SUBSTRACTION, new Subtraction());
        operationMap.put(AppConstants.MULTIPLICATION, new Multiplication());
        
        operationMap.put(AppConstants.ADDITION_CHAR, new Addition());
        operationMap.put(AppConstants.DIVISION_CHAR, new Division());
        operationMap.put(AppConstants.SUBSTRACTION_CHAR, new Subtraction());
        operationMap.put(AppConstants.MULTIPLICATION_CHAR, new Multiplication());
    }
 
    public static Optional<ArithmeticOperation> getOperation(String operator) {
        return Optional.ofNullable(operationMap.get(operator));
    }
    
}