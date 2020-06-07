package io.ykkh.calc.web.controller;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiParam;
import io.ykkh.calc.common.AppConstants;
import io.ykkh.calc.common.ArithmeticOperatorConstraint;
import io.ykkh.calc.service.CalculationServiceFactory;
import io.ykkh.calc.web.CalculationRequest;
import io.ykkh.calc.web.CalculationResult;
import io.ykkh.calc.web.ResponseInfo;

@RestController
@Validated
@RequestMapping(path = "${api.version}"+"/"+AppConstants.CALCULATION_API_PATH)
public class CalculationController {
	
	@Autowired
	@Qualifier(value = "arithmeticCalculationService")
	public CalculationServiceFactory calcService;

	@GetMapping
	public ResponseInfo calculateByGet(
			@RequestParam(name = "a") @Pattern(regexp = "^[0-9]\\d*(\\.\\d+)?$", message = "Param 'a' must be number.") @ApiParam(value = "First Number", example = "1", allowEmptyValue = false, required = true, type = "double") String a,
			@RequestParam(name = "b") @Pattern(regexp = "^[0-9]\\d*(\\.\\d+)?$", message = "Param 'b' must be number.") @ApiParam(value = "Second Number", example = "1", allowEmptyValue = false, required = true, type = "double") String b,
			@RequestParam(name = "op") @ArithmeticOperatorConstraint @ApiParam(value = "Operator", example = "add", allowableValues = "add, mul, div, sub", allowEmptyValue = false, required = true, type = "string") String op) {

		ResponseInfo response = new ResponseInfo();
		double result = calcService.calculate(Double.parseDouble(a), Double.parseDouble(b), op);
		CalculationResult calcResult = new CalculationResult(Double.parseDouble(a), Double.parseDouble(b), op, result);

		response.setData(calcResult);
		response.setMessage("Calculation Result");
		response.setStatus(HttpStatus.OK);

		return response;
	}

	@PostMapping
	public ResponseInfo calculateByPost(@Valid @RequestBody CalculationRequest request) {

		ResponseInfo response = new ResponseInfo();
		double a = Double.parseDouble(request.getA());
		double b = Double.parseDouble(request.getB());

		double result = calcService.calculate(a, b, request.getOp());
		CalculationResult calcResult = new CalculationResult(a, b, request.getOp(), result);

		response.setData(calcResult);
		response.setMessage("Calculation Result");
		response.setStatus(HttpStatus.OK);

		return response;
	}
	
	@GetMapping(path = "{a}/{b}/{op}")
	public ResponseInfo calculateByGetPath(
			@PathVariable(name = "a") @Pattern(regexp = "^[0-9]\\d*(\\.\\d+)?$", message = "Param 'a' must be number.") @ApiParam(value = "First Number", example = "1", allowEmptyValue = false, required = true, type = "double") String a,
			@PathVariable(name = "b") @Pattern(regexp = "^[0-9]\\d*(\\.\\d+)?$", message = "Param 'b' must be number.") @ApiParam(value = "Second Number", example = "1", allowEmptyValue = false, required = true, type = "double") String b,
			@PathVariable(name = "op") @ArithmeticOperatorConstraint @ApiParam(value = "Operator", example = "add", allowableValues = "add, mul, div, sub", allowEmptyValue = false, required = true, type = "string") String op) {

		ResponseInfo response = new ResponseInfo();
		double result = calcService.calculate(Double.parseDouble(a), Double.parseDouble(b), op);
		CalculationResult calcResult = new CalculationResult(Double.parseDouble(a), Double.parseDouble(b), op, result);

		response.setData(calcResult);
		response.setMessage("Calculation Result");
		response.setStatus(HttpStatus.OK);

		return response;
	}
	
	
	

}
