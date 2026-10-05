package com.opao.pp_api.features.business_type.dto.response;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;        

@JsonInclude(JsonInclude.Include.NON_NULL)
public record BusinessTypeResponse(

    @JsonProperty("id")
    Integer id,
    @JsonProperty("code")
    Integer code,
    @JsonProperty("description")
    String description
) {}
