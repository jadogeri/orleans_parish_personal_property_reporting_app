package com.opao.pp_api.features.business_type.dto.request;
import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.opao.pp_api.features.business_type.validation.ValidBusinessTypeCode;
import com.opao.pp_api.features.business_type.validation.ValidBusinessTypeDescription;

public record BusinessTypeUpdateRequest(

    @JsonProperty("code")
    @Schema(example = "123", description = "The unique code representing the business type")
    @ValidBusinessTypeCode.Optional Integer code,

    @JsonProperty("description")
    @Schema(example = "Retail", description = "The description of the business type")
    @ValidBusinessTypeDescription.Optional String description
) {}
