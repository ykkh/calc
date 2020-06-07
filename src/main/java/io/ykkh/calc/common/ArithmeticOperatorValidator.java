package io.ykkh.calc.common;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ArithmeticOperatorValidator implements
ConstraintValidator<ArithmeticOperatorConstraint, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		boolean result = false;
		
		if(value.equalsIgnoreCase(AppConstants.ADDITION) || value.equalsIgnoreCase(AppConstants.SUBSTRACTION) 
				||value.equalsIgnoreCase(AppConstants.MULTIPLICATION)|| value.equalsIgnoreCase(AppConstants.DIVISION)) {
			result = true;
		}
		
		return result;
	}

}
