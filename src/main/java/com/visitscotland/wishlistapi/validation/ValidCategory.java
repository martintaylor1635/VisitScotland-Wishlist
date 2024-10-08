package com.visitscotland.wishlistapi.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Retention(RUNTIME)
@Target({ PARAMETER, FIELD })
@Constraint(validatedBy = CategoryValidator.class)
public @interface ValidCategory {
    String message() default
        "The category should either be accommodation, attraction or event";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}