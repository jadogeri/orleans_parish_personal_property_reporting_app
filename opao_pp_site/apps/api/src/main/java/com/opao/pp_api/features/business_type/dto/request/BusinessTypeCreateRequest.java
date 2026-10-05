package com.opao.pp_api.features.business_type.dto.request;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.opao.pp_api.features.business_type.validation.ValidBusinessTypeCode;
import com.opao.pp_api.features.business_type.validation.ValidBusinessTypeDescription;

public record BusinessTypeCreateRequest(
    
    @JsonProperty("code")
    @ValidBusinessTypeCode Integer code,

    @JsonProperty("description")
    @ValidBusinessTypeDescription  String description
) {}
