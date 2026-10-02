package com.opao.pp_api.features.business_type.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {})
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@NotNull(message = "Business type code cannot be null") // 👈 Enforced for Create
@Positive(message = "Business type code must be a positive number")
public @interface ValidBusinessTypeCode {
    String message() default "Invalid business type code";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    // 💡 The Optional variant for Updates (Allows null, but validates if present)
    @Documented
    @Constraint(validatedBy = {})
    @Target({ElementType.FIELD, ElementType.PARAMETER})
    @Retention(RetentionPolicy.RUNTIME)
    @Positive(message = "Business type code must be a positive number") // 👈 No @NotNull here
    @interface Optional {
        String message() default "Invalid business type code";
        Class<?>[] groups() default {};
        Class<? extends Payload>[] payload() default {};
    }
}
