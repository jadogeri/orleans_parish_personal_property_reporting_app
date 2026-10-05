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
@NotBlank(message = "Email address cannot be blank")
@Pattern(regexp = ValidationRegexConstants.EMAIL_REGEX, message = "Invalid email address format")
public @interface ValidEmail {
    String message() default "Invalid email address";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    @Documented
    @Constraint(validatedBy = {})
    @Target({ElementType.FIELD, ElementType.PARAMETER})
    @Retention(RetentionPolicy.RUNTIME)
    @Pattern(regexp = ValidationRegexConstants.EMAIL_REGEX, message = "Invalid email address format")
    @interface Optional {
        String message() default "Invalid email address";
        Class<?>[] groups() default {};
        Class<? extends Payload>[] payload() default {};
    }
}
