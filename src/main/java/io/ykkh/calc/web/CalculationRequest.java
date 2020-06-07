package io.ykkh.calc.web;

import javax.validation.constraints.Pattern;

import io.swagger.annotations.ApiModelProperty;
import io.ykkh.calc.common.ArithmeticOperatorConstraint;

public class CalculationRequest {
	@Pattern(regexp = "^[0-9]\\d*(\\.\\d+)?$", message = "Param 'a' must be number.")
	@ApiModelProperty(value = "First Number", example = "1", allowEmptyValue = false, required = true, dataType = "double")
	private String a;
	
	@Pattern(regexp = "^[0-9]\\d*(\\.\\d+)?$", message = "Param 'b' must be number.")
	@ApiModelProperty(value = "Second Number", example = "1", allowEmptyValue = false, required = true, dataType = "double")
	private String b;

	@ArithmeticOperatorConstraint
	@ApiModelProperty(value = "Operator", example = "add", allowableValues = "add, mul, div, sub", allowEmptyValue = false, required = true, dataType = "string")
	private String op;

	public CalculationRequest(String a, String b, String op) {
		super();
		this.a = a;
		this.b = b;
		this.op = op;
	}

	public String getA() {
		return a;
	}

	public void setA(String a) {
		this.a = a;
	}

	public String getB() {
		return b;
	}

	public void setB(String b) {
		this.b = b;
	}

	public String getOp() {
		return op;
	}

	public void setOp(String op) {
		this.op = op;
	}

}
