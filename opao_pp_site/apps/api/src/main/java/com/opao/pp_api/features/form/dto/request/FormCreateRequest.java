package com.opao.pp_api.features.form.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.opao.pp_api.features.form.validation.ValidFormTitle;
import com.opao.pp_api.features.form.validation.ValidBillNumber;
import com.opao.pp_api.features.form.validation.ValidFormPin;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FormCreateRequest {

    @ValidFormTitle
    private String title;

    @NotNull(message = "Filing year is required")
    private Integer filingYear;

    @ValidBillNumber
    private String billNumber;

    @ValidFormPin
    private String pin;

    @NotNull(message = "Form type ID is required")
    private Integer formTypeId;

    @NotNull(message = "User ID is required")
    private Integer userId;
}
