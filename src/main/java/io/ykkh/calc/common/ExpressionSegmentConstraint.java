package io.ykkh.calc.common;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = ExpressionSegmentContraintValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.CONSTRUCTOR, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ExpressionSegmentConstraint {
    String message() default "Invalid Expression Object";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}