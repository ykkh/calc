package io.ykkh.calc.service;

public class Multiplication implements ArithmeticOperation {

	@Override
	public double apply(double a, double b) {
		return a*b;
	}

}
