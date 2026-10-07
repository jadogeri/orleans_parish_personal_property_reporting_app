package com.opao.pp_api.features.form.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.opao.pp_api.features.form.validation.ValidFormTitle;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.opao.pp_api.features.form.validation.ValidBillNumber;
import com.opao.pp_api.features.form.validation.ValidFormPin;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FormCreateRequest {

    @ValidFormTitle
    @JsonProperty("title")
    private String title;

    @NotNull(message = "Filing year is required")
    @JsonProperty("filingYear")
    private Integer filingYear;

    @ValidBillNumber
    @JsonProperty("billNumber")
    private String billNumber;

    @ValidFormPin
    @JsonProperty("securityPin")
    private String pin;

    @NotNull(message = "Form type ID is required")
    @JsonProperty("formTypeId")
    private Integer formTypeId;

    @NotNull(message = "User ID is required")
    @JsonProperty("userId")
    private Integer userId;
}
