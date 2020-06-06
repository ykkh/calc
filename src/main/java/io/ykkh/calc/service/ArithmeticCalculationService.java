package io.ykkh.calc.service;

import org.springframework.stereotype.Service;

@Service (value = "arithmeticCalculationService")
public class ArithmeticCalculationService implements CalculationServiceFactory {

	@Override
	public double calculate(double a, double b, String operator) {
		 ArithmeticOperation targetOperation = ArithmeticOperatorFactory
			      .getOperation(operator)
			      .orElseThrow(() -> new IllegalArgumentException("Invalid Operator"));
		 
			    return targetOperation.apply(a, b);
	}

}
