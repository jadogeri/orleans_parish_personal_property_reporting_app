package com.opao.pp_api.features.property_asset.dto.request;

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

    @NotNull(message = "Asset ID is required for updates")
    private Integer id;

    private Integer sectionNumber;

    @Size(min = PropertyAssetConstants.CATEGORY_MIN_LENGTH, max = PropertyAssetConstants.CATEGORY_MAX_LENGTH,
          message = "Category length must be between {min} and {max} characters")
    private String category;

    @Size(min = PropertyAssetConstants.PPTYPE_MIN_LENGTH, max = PropertyAssetConstants.PPTYPE_MAX_LENGTH,
          message = "Property type length must be between {min} and {max} characters")
    private String propertyType; 

    @Size(min = PropertyAssetConstants.ASSET_DESCRIPTION_MIN_LENGTH, max = PropertyAssetConstants.ASSET_DESCRIPTION_MAX_LENGTH,
          message = "Asset description length must be between {min} and {max} characters")
    private String assetDescription;

    private Integer effectiveLife;
}
