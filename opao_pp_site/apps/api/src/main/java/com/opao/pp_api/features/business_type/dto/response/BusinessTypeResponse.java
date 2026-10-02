package com.opao.pp_api.features.business_type.dto.response;
import com.fasterxml.jackson.annotation.JsonInclude;        

@JsonInclude(JsonInclude.Include.NON_NULL)
public record BusinessTypeResponse(
    Integer id,
    Integer code,
    String description
) {}
