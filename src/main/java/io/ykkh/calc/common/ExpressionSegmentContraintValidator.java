package io.ykkh.calc.common;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.ykkh.calc.web.ExpressionRequest;
import io.ykkh.calc.web.ExpressionSegment;

public class ExpressionSegmentContraintValidator implements ConstraintValidator<ExpressionSegmentConstraint, Object> {

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {

		try {
			ExpressionRequest er = (ExpressionRequest) value;

			List<ExpressionSegment> list = er.getExpressionSegments();

			if (isValidSegList(list)) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
		//	context.buildConstraintViolationWithTemplate("").addConstraintViolation();
			return false;
		}
		
	}

	@SuppressWarnings({ "unchecked" })
	private boolean isValidSegList(List<ExpressionSegment> list) {

		ObjectMapper mapper = new ObjectMapper();
		try {
			List<ExpressionSegment> driverlocationsList = mapper.convertValue(list,
					new TypeReference<List<ExpressionSegment>>() {
					});

			for (ExpressionSegment es : driverlocationsList) {
				if (!Utils.isNumeric(es.getNum())) {

					List<ExpressionSegment> esList = (List<ExpressionSegment>) es.getNum();
					if (isValidSegList(esList))
						return true;
					else
						return false;

				}
				
				if(es.getNextOp() != null) {
					if ( !es.getNextOp().equals(AppConstants.ADDITION_CHAR) && !es.getNextOp().equals(AppConstants.SUBSTRACTION_CHAR)
							&& !es.getNextOp().equals(AppConstants.MULTIPLICATION_CHAR)
							&& !es.getNextOp().equals(AppConstants.DIVISION_CHAR) ) {
						return false;
					}
				}

			
			}

		} catch (Exception e) {
			return false;
		}
		return true;
	}

}
