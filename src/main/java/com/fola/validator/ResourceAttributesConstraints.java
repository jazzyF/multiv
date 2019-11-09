package com.fola.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Constraint(validatedBy = ResourceConstraintValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface ResourceAttributesConstraints {
    String message() default "Invalid Resource Payload";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
