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
@NotBlank(message = "Full name cannot be blank")
@Pattern(regexp = ValidationRegexConstants.FULL_NAME_REGEX, message = "Full name must be 7-50 characters long and cannot contain consecutive or trailing spaces")
public @interface ValidFullName {
    String message() default "Invalid full name";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    @Documented
    @Constraint(validatedBy = {})
    @Target({ElementType.FIELD, ElementType.PARAMETER})
    @Retention(RetentionPolicy.RUNTIME)
    @Pattern(regexp = ValidationRegexConstants.FULL_NAME_REGEX, message = "Full name must be 7-50 characters long and cannot contain consecutive or trailing spaces")
    @interface Optional {
        String message() default "Invalid full name";
        Class<?>[] groups() default {};
        Class<? extends Payload>[] payload() default {};
    }
}
