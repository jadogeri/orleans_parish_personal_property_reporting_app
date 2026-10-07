package com.opao.pp_api.features.business_type.dto.request;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.opao.pp_api.features.business_type.validation.ValidBusinessTypeCode;
import com.opao.pp_api.features.business_type.validation.ValidBusinessTypeDescription;

import io.swagger.v3.oas.annotations.media.Schema;

public record BusinessTypeCreateRequest(
    
    @JsonProperty("code")
    @Schema(example = "123", description = "The unique code representing the business type")
    @ValidBusinessTypeCode Integer code,

    @JsonProperty("description")
    @Schema(example = "Retail", description = "The description of the business type")
    @ValidBusinessTypeDescription  String description
) {}
