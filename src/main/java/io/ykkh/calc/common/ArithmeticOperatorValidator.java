package io.ykkh.calc.common;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ArithmeticOperatorValidator implements
ConstraintValidator<ArithmeticOperatorConstraint, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		boolean result = false;
		
		if(value.equalsIgnoreCase(CalcAppConstants.ADDITION) || value.equalsIgnoreCase(CalcAppConstants.SUBSTRACTION) 
				||value.equalsIgnoreCase(CalcAppConstants.MULTIPLICATION)|| value.equalsIgnoreCase(CalcAppConstants.DIVISION)) {
			result = true;
		}
		
		return result;
	}

}
