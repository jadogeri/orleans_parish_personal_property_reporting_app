package com.opao.pp_api.features.form.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.lang.annotation.*;

import com.opao.pp_api.features.form.constants.FormConstants;

@Documented
@Constraint(validatedBy = {})
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@NotBlank(message = "PIN cannot be blank")
@Size(min = FormConstants.PIN_MIN_LENGTH, max = FormConstants.PIN_MAX_LENGTH, 
      message = "PIN must be between " + FormConstants.PIN_MIN_LENGTH + " and " + FormConstants.PIN_MAX_LENGTH + " characters")
public @interface ValidFormPin {
    String message() default "Invalid PIN";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    @Documented
    @Constraint(validatedBy = {})
    @Target({ElementType.FIELD, ElementType.PARAMETER})
    @Retention(RetentionPolicy.RUNTIME)
    @Size(min = FormConstants.PIN_MIN_LENGTH, max = FormConstants.PIN_MAX_LENGTH, 
          message = "PIN must be between " + FormConstants.PIN_MIN_LENGTH + " and " + FormConstants.PIN_MAX_LENGTH + " characters")
    @interface Optional {
        String message() default "Invalid PIN";
        Class<?>[] groups() default {};
        Class<? extends Payload>[] payload() default {};
    }
}
