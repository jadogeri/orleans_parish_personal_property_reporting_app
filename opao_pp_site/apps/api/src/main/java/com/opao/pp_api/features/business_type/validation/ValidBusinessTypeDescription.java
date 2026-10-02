package com.opao.pp_api.features.business_type.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.lang.annotation.*;

import com.opao.pp_api.features.business_type.constants.BusinessTypeConstants;

@Documented
@Constraint(validatedBy = {})
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@NotBlank(message = "Business type description cannot be empty") // 👈 Enforced for Create
@Size(
    min = BusinessTypeConstants.BUSINESS_DESCRIPTION_MIN_LENGTH, 
    max = BusinessTypeConstants.BUSINESS_DESCRIPTION_MAX_LENGTH,
    message = "Description must be between " + BusinessTypeConstants.BUSINESS_DESCRIPTION_MIN_LENGTH + " and " + BusinessTypeConstants.BUSINESS_DESCRIPTION_MAX_LENGTH + " characters"
)
public @interface ValidBusinessTypeDescription {
    String message() default "Invalid business type description";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    // 💡 The Optional variant for Updates (Allows null/blank strings to pass, but validates length if sent)
    @Documented
    @Constraint(validatedBy = {})
    @Target({ElementType.FIELD, ElementType.PARAMETER})
    @Retention(RetentionPolicy.RUNTIME)
    @Size(
        min = BusinessTypeConstants.BUSINESS_DESCRIPTION_MIN_LENGTH, 
        max = BusinessTypeConstants.BUSINESS_DESCRIPTION_MAX_LENGTH,
        message = "Description must be between " + BusinessTypeConstants.BUSINESS_DESCRIPTION_MIN_LENGTH + " and " + BusinessTypeConstants.BUSINESS_DESCRIPTION_MAX_LENGTH + " characters"
    ) // 👈 No @NotBlank here
    @interface Optional {
        String message() default "Invalid business type description";
        Class<?>[] groups() default {};
        Class<? extends Payload>[] payload() default {};
    }
}
