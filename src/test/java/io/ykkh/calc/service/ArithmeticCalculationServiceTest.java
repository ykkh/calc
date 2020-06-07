package io.ykkh.calc.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import io.ykkh.calc.common.AppConstants;

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
		result = calcService.calculate(a, b, AppConstants.ADDITION);
		assertEquals(new Double(a), new Double(result));
	}

	@Test
	public void testSubtraction() {
		result = calcService.calculate(a, b, AppConstants.SUBSTRACTION);
		assertEquals(new Double(a), new Double(result));
	}

	@Test
	public void testMultiplication() {
		result = calcService.calculate(a, b, AppConstants.MULTIPLICATION);
		assertEquals(new Double(b), new Double(result));
	}

	@Test
	public void testDivisionException() {
		assertThrows(ArithmeticException.class, () -> {
			calcService.calculate(a, b,  AppConstants.DIVISION);
		});
	}
	
	@Test
	public void testDivision() {
		b = 2;
		double expected_result = 5;
		result = calcService.calculate(a, b, AppConstants.DIVISION);
		assertEquals(new Double(expected_result), new Double(result));
	}
}
