package io.ykkh.calc.service;

public class Addition implements ArithmeticOperation {

	@Override
	public double apply(double a, double b) {
		return a+b;
	}

}
