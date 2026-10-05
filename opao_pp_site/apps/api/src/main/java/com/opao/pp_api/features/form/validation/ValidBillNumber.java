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
@NotBlank(message = "Bill number cannot be blank")
@Size(min = FormConstants.BILL_NUMBER_MIN_LENGTH, max = FormConstants.BILL_NUMBER_MAX_LENGTH, 
      message = "Bill number must be between " + FormConstants.BILL_NUMBER_MIN_LENGTH + " and " + FormConstants.BILL_NUMBER_MAX_LENGTH + " characters")
public @interface ValidBillNumber {
    String message() default "Invalid bill number";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    @Documented
    @Constraint(validatedBy = {})
    @Target({ElementType.FIELD, ElementType.PARAMETER})
    @Retention(RetentionPolicy.RUNTIME)
    @Size(min = FormConstants.BILL_NUMBER_MIN_LENGTH, max = FormConstants.BILL_NUMBER_MAX_LENGTH, 
          message = "Bill number must be between " + FormConstants.BILL_NUMBER_MIN_LENGTH + " and " + FormConstants.BILL_NUMBER_MAX_LENGTH + " characters")
    @interface Optional {
        String message() default "Invalid bill number";
        Class<?>[] groups() default {};
        Class<? extends Payload>[] payload() default {};
    }
}
