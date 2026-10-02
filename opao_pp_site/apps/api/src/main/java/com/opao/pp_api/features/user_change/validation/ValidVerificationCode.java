package com.opao.pp_api.features.user_change.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.lang.annotation.*;
import com.opao.pp_api.features.user_change.constants.UserChangeConstants;

@Documented
@Constraint(validatedBy = {})
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@NotBlank(message = "Verification code cannot be empty")
@Size(
    min = UserChangeConstants.VERIFICATION_CODE_MIN_LENGTH, 
    max = UserChangeConstants.VERIFICATION_CODE_MAX_LENGTH,
    message = "Verification code must be between " + UserChangeConstants.VERIFICATION_CODE_MIN_LENGTH + " and " + UserChangeConstants.VERIFICATION_CODE_MAX_LENGTH + " characters"
)
public @interface ValidVerificationCode {
    String message() default "Invalid verification code";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    @Documented
    @Constraint(validatedBy = {})
    @Target({ElementType.FIELD, ElementType.PARAMETER})
    @Retention(RetentionPolicy.RUNTIME)
    @Size(
        min = UserChangeConstants.VERIFICATION_CODE_MIN_LENGTH, 
        max = UserChangeConstants.VERIFICATION_CODE_MAX_LENGTH,
        message = "Verification code must be between " + UserChangeConstants.VERIFICATION_CODE_MIN_LENGTH + " and " + UserChangeConstants.VERIFICATION_CODE_MAX_LENGTH + " characters"
    )
    @interface Optional {
        String message() default "Invalid verification code";
        Class<?>[] groups() default {};
        Class<? extends Payload>[] payload() default {};
    }
}
