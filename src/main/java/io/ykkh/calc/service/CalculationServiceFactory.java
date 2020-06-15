package io.ykkh.calc.service;

import java.util.List;

import io.ykkh.calc.web.ExpressionSegment;

public interface  CalculationServiceFactory {
	public double calculate(String operator, double b, double a);
	
	public double calculate(List<ExpressionSegment> es);
}
