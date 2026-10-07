package com.opao.pp_api.common.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size; // 💡 Imported
import java.lang.annotation.*;

import com.opao.pp_api.common.constants.ValidationRegexConstants;
import com.opao.pp_api.common.constants.ValidationRangeConstants; // 💡 Imported

import io.swagger.v3.oas.annotations.media.Schema;

@Documented
@Constraint(validatedBy = {})
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@NotBlank(message = "Email address cannot be blank")
@Pattern(regexp = ValidationRegexConstants.EMAIL_REGEX, message = "Invalid email address format")
// 💡 Enforce the maximum length limit at the core annotation level
@Size(max = ValidationRangeConstants.EMAIL_ADDRESS_MAX_LENGTH, message = "Email address exceeds maximum allowed length")
public @interface ValidEmail {
    String message() default "Invalid email address";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    @Documented
    @Constraint(validatedBy = {})
    @Target({ElementType.FIELD, ElementType.PARAMETER})
    @Retention(RetentionPolicy.RUNTIME)
    @Pattern(regexp = ValidationRegexConstants.EMAIL_REGEX, message = "Invalid email address format")
    // 💡 Add the identical size constraint safely for your optional fields
    @Size(max = ValidationRangeConstants.EMAIL_ADDRESS_MAX_LENGTH, message = "Email address exceeds maximum allowed length")
    @interface Optional {
        String message() default "Invalid email address";
        Class<?>[] groups() default {};
        Class<? extends Payload>[] payload() default {};
    }
}
