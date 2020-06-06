package io.ykkh.calc.common;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ArithmeticOperatorValidator implements
ConstraintValidator<ArithmeticOperatorConstraint, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		boolean result = false;
		
		if(value.equalsIgnoreCase("add") || value.equalsIgnoreCase("sub") 
				||value.equalsIgnoreCase("mul")|| value.equalsIgnoreCase("div")) {
			result = true;
		}
		
		return result;
	}

}
