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
import com.opao.pp_api.common.validation.ValidForeignId;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FormUpdateRequest {

    @NotNull(message = "Form ID is required for updates")
    private Integer id;

    @ValidFormTitle.Optional
    private String title;

    // Kept optional implicitly (Integer can be null unless marked @NotNull)
    private Integer filingYear;

    @ValidBillNumber.Optional
    private String billNumber;

    @ValidFormPin.Optional
    private String pin;

    @ValidForeignId.Optional
    private Integer formTypeId;
}
