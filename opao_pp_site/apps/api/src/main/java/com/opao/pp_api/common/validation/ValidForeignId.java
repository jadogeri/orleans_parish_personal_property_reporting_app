package com.opao.pp_api.common.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {})
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@NotNull(message = "ID cannot be null")
@Positive(message = "ID must be a positive number")
public @interface ValidForeignId {
    String message() default "Invalid linked reference ID";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    @Documented
    @Constraint(validatedBy = {})
    @Target({ElementType.FIELD, ElementType.PARAMETER})
    @Retention(RetentionPolicy.RUNTIME)
    @Positive(message = "ID must be a positive number")
    @interface Optional {
        String message() default "Invalid linked reference ID";
        Class<?>[] groups() default {};
        Class<? extends Payload>[] payload() default {};
    }
}
