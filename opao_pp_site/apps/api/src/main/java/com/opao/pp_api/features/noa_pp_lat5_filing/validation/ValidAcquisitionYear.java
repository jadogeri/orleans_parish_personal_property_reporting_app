package com.opao.pp_api.features.noa_pp_lat5_filing.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import java.lang.annotation.*;

import com.opao.pp_api.features.noa_pp_lat5_filing.constants.NoaPpLat5FilingConstants;

@Documented
@Constraint(validatedBy = {})
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@NotNull(message = "Year of acquisition cannot be null")
@Min(value = NoaPpLat5FilingConstants.MIN_YEAR_OF_ACQUISITION, message = "Year of acquisition must be 1920 or later")
public @interface ValidAcquisitionYear {
    String message() default "Invalid year of acquisition";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    @Documented
    @Constraint(validatedBy = {})
    @Target({ElementType.FIELD, ElementType.PARAMETER})
    @Retention(RetentionPolicy.RUNTIME)
    @Min(value = NoaPpLat5FilingConstants.MIN_YEAR_OF_ACQUISITION, message = "Year of acquisition must be 1920 or later")
    @interface Optional {
        String message() default "Invalid year of acquisition";
        Class<?>[] groups() default {};
        Class<? extends Payload>[] payload() default {};
    }
}
