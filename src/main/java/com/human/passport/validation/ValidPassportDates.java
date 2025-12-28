package com.human.passport.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PassportDatesValidator.class)
public @interface ValidPassportDates {
    String message() default "Expire date must be after issue date";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
