package com.opao.pp_api.common.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import java.lang.annotation.*;

import com.opao.pp_api.common.constants.ValidationRegexConstants;

@Documented
@Constraint(validatedBy = {})
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@NotBlank(message = "Jurisdiction code cannot be blank")
@Pattern(regexp = ValidationRegexConstants.JURISDICTION_REGEX, message = "Jurisdiction code must be alphanumeric and up to 6 characters long")
public @interface ValidJurisdiction {
    String message() default "Invalid jurisdiction code";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    @Documented
    @Constraint(validatedBy = {})
    @Target({ElementType.FIELD, ElementType.PARAMETER})
    @Retention(RetentionPolicy.RUNTIME)
    @Pattern(regexp = ValidationRegexConstants.JURISDICTION_REGEX, message = "Jurisdiction code must be alphanumeric and up to 6 characters long")
    @interface Optional {
        String message() default "Invalid jurisdiction code";
        Class<?>[] groups() default {};
        Class<? extends Payload>[] payload() default {};
    }
}
