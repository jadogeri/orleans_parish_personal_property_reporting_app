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
@NotBlank(message = "Title cannot be blank")
@Size(min = FormConstants.TITLE_MIN_LENGTH, max = FormConstants.TITLE_MAX_LENGTH, 
      message = "Title must be between " + FormConstants.TITLE_MIN_LENGTH + " and " + FormConstants.TITLE_MAX_LENGTH + " characters")
public @interface ValidFormTitle {
    String message() default "Invalid form title";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    @Documented
    @Constraint(validatedBy = {})
    @Target({ElementType.FIELD, ElementType.PARAMETER})
    @Retention(RetentionPolicy.RUNTIME)
    @Size(min = FormConstants.TITLE_MIN_LENGTH, max = FormConstants.TITLE_MAX_LENGTH, 
          message = "Title must be between " + FormConstants.TITLE_MIN_LENGTH + " and " + FormConstants.TITLE_MAX_LENGTH + " characters")
    @interface Optional {
        String message() default "Invalid form title";
        Class<?>[] groups() default {};
        Class<? extends Payload>[] payload() default {};
    }
}
