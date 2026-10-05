package com.opao.pp_api.features.property_asset.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;       
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record PropertyAssetResponse(
    @JsonProperty("id")
    Integer id,
    @JsonProperty("sectionNumber")
    Integer sectionNumber,
    @JsonProperty("category")
    String category,
    @JsonProperty("propertyType")
    String propertyType,
    @JsonProperty("assetDescription")
    String assetDescription,
    @JsonProperty("effectiveLife")
    Integer effectiveLife
) {}
