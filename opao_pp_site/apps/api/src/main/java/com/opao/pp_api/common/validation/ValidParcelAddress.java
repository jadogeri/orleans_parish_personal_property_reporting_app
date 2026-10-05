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
@NotBlank(message = "Parcel ID cannot be blank")
@Pattern(regexp = ValidationRegexConstants.PARCEL_ADDRESS_REGEX, message = "Parcel ID format is invalid (Max 30 alphanumeric/hyphen characters)")
public @interface ValidParcelAddress {
    String message() default "Invalid parcel ID";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    @Documented
    @Constraint(validatedBy = {})
    @Target({ElementType.FIELD, ElementType.PARAMETER})
    @Retention(RetentionPolicy.RUNTIME)
    @Pattern(regexp = ValidationRegexConstants.PARCEL_ADDRESS_REGEX, message = "Parcel ID format is invalid (Max 30 alphanumeric/hyphen characters)")
    @interface Optional {
        String message() default "Invalid parcel ID";
        Class<?>[] groups() default {};
        Class<? extends Payload>[] payload() default {};
    }
}
