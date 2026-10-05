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
@NotBlank(message = "Password cannot be blank")
@Pattern(regexp = ValidationRegexConstants.PASSWORD_REGEX, message = "Password must be 8-32 characters long and contain at least one uppercase letter, one lowercase letter, one number, and one special character")
public @interface ValidPassword {
    String message() default "Invalid password matching rule";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    @Documented
    @Constraint(validatedBy = {})
    @Target({ElementType.FIELD, ElementType.PARAMETER})
    @Retention(RetentionPolicy.RUNTIME)
    @Pattern(regexp = ValidationRegexConstants.PASSWORD_REGEX, message = "Password must be 8-32 characters long and contain at least one uppercase letter, one lowercase letter, one number, and one special character")
    @interface Optional {
        String message() default "Invalid password matching rule";
        Class<?>[] groups() default {};
        Class<? extends Payload>[] payload() default {};
    }
}
