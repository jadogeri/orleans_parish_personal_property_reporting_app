package com.opao.pp_api.features.property_asset.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.opao.pp_api.features.property_asset.constants.PropertyAssetConstants;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PropertyAssetUpdateRequest {

    @JsonProperty("id")
    @NotNull(message = "Asset ID is required for updates")
    private Integer id;

    @JsonProperty("sectionNumber")
    private Integer sectionNumber;

    @Size(min = PropertyAssetConstants.CATEGORY_MIN_LENGTH, max = PropertyAssetConstants.CATEGORY_MAX_LENGTH,
          message = "Category length must be between {min} and {max} characters")
    @JsonProperty("category")
    private String category;

    @Size(min = PropertyAssetConstants.PPTYPE_MIN_LENGTH, max = PropertyAssetConstants.PPTYPE_MAX_LENGTH,
          message = "Property type length must be between {min} and {max} characters")
    @JsonProperty("propertyType")
    private String propertyType; 

    @Size(min = PropertyAssetConstants.ASSET_DESCRIPTION_MIN_LENGTH, max = PropertyAssetConstants.ASSET_DESCRIPTION_MAX_LENGTH,
          message = "Asset description length must be between {min} and {max} characters")
    @JsonProperty("assetDescription")
    private String assetDescription;

    @JsonProperty("effectiveLife")
    private Integer effectiveLife;
}
