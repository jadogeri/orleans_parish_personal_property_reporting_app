package com.opao.pp_api.features.property_asset.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record PropertyAssetResponse(
    Integer id,
    Integer sectionNumber,
    String category,
    String propertyType,
    String assetDescription,
    Integer effectiveLife
) {}
