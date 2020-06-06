package io.ykkh.calc.common;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = ArithmeticOperatorValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER  })
@Retention(RetentionPolicy.RUNTIME)
public @interface ArithmeticOperatorConstraint {
    String message() default "Invalid Operator";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}