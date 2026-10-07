package com.opao.pp_api.features.business_type.dto.response;
import com.fasterxml.jackson.annotation.JsonProperty;        
import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record BusinessTypeResponse(

    @JsonProperty("id")
    @Schema(example = "1", description = "The unique identifier of the business type")
    Integer id,
    @JsonProperty("code")
    @Schema(example = "123", description = "The unique code representing the business type")
    Integer code,
    @JsonProperty("description")
    @Schema(example = "Retail", description = "The description of the business type")
    String description
) {}
