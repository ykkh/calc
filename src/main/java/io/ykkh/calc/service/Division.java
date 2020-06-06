package io.ykkh.calc.service;

public class Division implements ArithmeticOperation {

	@Override
	public double apply(double a, double b) {
		
		if(b == 0.0) {
			throw new ArithmeticException("Cannot Divided By Zero");
		}
		
		return a/b;
	}

}
