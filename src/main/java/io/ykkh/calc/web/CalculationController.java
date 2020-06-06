package io.ykkh.calc.web;

import javax.validation.constraints.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.ykkh.calc.common.ArithmeticOperatorConstraint;
import io.ykkh.calc.service.CalculationServiceFactory;

@RestController
@Validated
@RequestMapping(path = "v2/calc")
public class CalculationController {

	
@Autowired
@Qualifier(value = "arithmeticCalculationService")
public CalculationServiceFactory calcService;

	@GetMapping
	public ResponseInfo calculateByGet(
			@RequestParam(name = "a") @Pattern(regexp = "^[0-9]\\d*(\\.\\d+)?$", message = "Param 'a' must be number.") String a, 
			@RequestParam(name = "b") @Pattern(regexp = "^[0-9]\\d*(\\.\\d+)?$", message = "Param 'b' must be number.") String b,
			@RequestParam(name= "op") @ArithmeticOperatorConstraint String op
			) {
		
		ResponseInfo response = new ResponseInfo();
		double result = calcService.calculate(Double.parseDouble(a), Double.parseDouble(b), op);
		CalculationResult  calcResult = new CalculationResult(Double.parseDouble(a), Double.parseDouble(b), op, result);
		
		response.setData(calcResult);
		response.setMessage("Calculation Result");
		response.setStatus(HttpStatus.OK);
	
		
		return response;
	}
	
	
	

}
