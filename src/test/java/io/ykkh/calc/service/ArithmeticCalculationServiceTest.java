package io.ykkh.calc.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;


import io.ykkh.calc.common.AppConstants;
import io.ykkh.calc.common.Utils;

@SpringBootTest
public class ArithmeticCalculationServiceTest {

	@Autowired
	@Qualifier(value = "arithmeticCalculationService")
	public CalculationServiceFactory calcService;

	private double a = 10;
	private double b = 1;
	private double result;

	@BeforeEach
	public void initTest() {
		a = 10;
		b = 0;
	}

	@Test
	public void testAddition() {
		result = calcService.calculate(AppConstants.ADDITION, b, a);
		assertEquals(new Double(a), new Double(result));
	}

	@Test
	public void testSubtraction() {
		result = calcService.calculate(AppConstants.SUBSTRACTION, b, a);
		assertEquals(new Double(a), new Double(result));
	}

	@Test
	public void testMultiplication() {
		result = calcService.calculate(AppConstants.MULTIPLICATION, b, a);
		assertEquals(new Double(b), new Double(result));
	}

	@Test
	public void testDivisionException() {
		assertThrows(ArithmeticException.class, () -> {
			calcService.calculate(AppConstants.DIVISION, b,  a);
		});
	}
	
	@Test
	public void testDivision() {
		b = 2;
		double expected_result = 5;
		result = calcService.calculate(AppConstants.DIVISION, b, a);
		assertEquals(new Double(expected_result), new Double(result));
	}
	
	
	@Test
	public void testExpression() {
		result = calcService.calculate(Utils.stringToObject("3*4-2*5"));
		assertEquals(2, new Double(result));
		result = calcService.calculate(Utils.stringToObject("3*(4-2)*5"));
		assertEquals(30, new Double(result));
		
		
	}
}
