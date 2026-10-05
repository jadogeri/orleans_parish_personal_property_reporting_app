package com.opao.pp_api.features.business_type.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.opao.pp_api.features.business_type.validation.ValidBusinessTypeCode;
import com.opao.pp_api.features.business_type.validation.ValidBusinessTypeDescription;

public record BusinessTypeUpdateRequest(

    @JsonProperty("code")
    @ValidBusinessTypeCode.Optional Integer code,

    @JsonProperty("description")
    @ValidBusinessTypeDescription.Optional String description
) {}
