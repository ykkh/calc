package io.ykkh.calc.service;

import java.util.List;
import java.util.Stack;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.ykkh.calc.common.AppConstants;
import io.ykkh.calc.common.Utils;
import io.ykkh.calc.web.ExpressionSegment;

@Service(value = "arithmeticCalculationService")
public class ArithmeticCalculationService implements CalculationServiceFactory {

	@Override
	public double calculate(String operator, double b, double a) {
		ArithmeticOperation targetOperation = ArithmeticOperatorFactory.getOperation(operator)
				.orElseThrow(() -> new IllegalArgumentException("Invalid Operator"));

		return targetOperation.apply(a, b);
	}

	public double calculate(List<ExpressionSegment> expressionSegments) {
		Stack<Double> numbers = new Stack<Double>();
		Stack<String> ops = new Stack<String>();
		
		ObjectMapper mapper = new ObjectMapper();
			List<ExpressionSegment> driverlocationsList = mapper.convertValue(expressionSegments,
					new TypeReference<List<ExpressionSegment>>() {
					});

		
		for (ExpressionSegment expr : driverlocationsList) {
			if (Utils.isNumeric(expr.getNum())) {
				Double d =  Double.parseDouble(expr.getNum().toString());
				try {
					numbers.push(d);
				} catch (ClassCastException e) {
					numbers.push(Double.parseDouble((String) expr.getNum()));
				}
			} else {
				@SuppressWarnings("unchecked")
				double d = calculate((List<ExpressionSegment>) expr.getNum()); // recursive method call for Parentheses
				numbers.push(d);
			}
			if (expr.getNextOp() != null && !expr.getNextOp().isEmpty()) {

				if (ops.isEmpty()) {
					ops.push(expr.getNextOp());
				} else {
					if (hasPrecedence(ops.peek(), expr.getNextOp())) {
						numbers.push(calculate(ops.pop(), numbers.pop(), numbers.pop()));
						ops.push(expr.getNextOp());
					} else {
						// if next op has Precedence over the one on the top of the Stack
						ops.push(expr.getNextOp());
					}
				}

			} else if (expr.getNextOp() == null) { // for the last number in the expression
				numbers.push(calculate(ops.pop(), numbers.pop(), numbers.pop()));
			}
		}
		while (!ops.empty()) {
			numbers.push(calculate(ops.pop(), numbers.pop(), numbers.pop()));
		}
		return numbers.peek();

	}

	public static boolean hasPrecedence(String op1, String op2) {

		if ((op1.equals(AppConstants.MULTIPLICATION_CHAR) || op1.equals(AppConstants.DIVISION_CHAR))
				&& (op2.equals(AppConstants.ADDITION_CHAR) || op2.equals(AppConstants.SUBSTRACTION_CHAR)))
			return true;
		else
			return false;
	}

}
