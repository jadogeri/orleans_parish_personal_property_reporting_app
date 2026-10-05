package com.opao.pp_api.features.property_asset.mapper;

import org.springframework.stereotype.Component;
import com.opao.pp_api.features.property_asset.dto.request.PropertyAssetCreateRequest;
import com.opao.pp_api.features.property_asset.dto.request.PropertyAssetUpdateRequest;
import com.opao.pp_api.features.property_asset.dto.response.PropertyAssetResponse;
import com.opao.pp_api.features.property_asset.model.PropertyAsset;

@Component
public class PropertyAssetDtoMapper {

    /**
     * Maps a creation payload request into a clean PropertyAsset Domain Model instance.
     * Primitives default to 0 if null bounds are passed before validation triggers.
     */
    public PropertyAsset toDomain(PropertyAssetCreateRequest request) {
        if (request == null) return null;

        return PropertyAsset.builder()
                .sectionNumber(request.getSectionNumber() != null ? request.getSectionNumber() : 0)
                .category(request.getCategory())
                .propertyType(request.getPropertyType())
                .assetDescription(request.getAssetDescription())
                .effectiveLife(request.getEffectiveLife() != null ? request.getEffectiveLife() : 0)
                .build();
    }

    /**
     * Maps a path-bound ID variable alongside an update payload request into a Domain Model instance.
     * Keeps unassigned wrappers as null so your Service layer can skip updating missing fields.
     */
    public PropertyAsset toDomain(Integer id, PropertyAssetUpdateRequest request) {
        if (request == null) return null;

        return PropertyAsset.builder()
                .id(id)
                .sectionNumber(request.getSectionNumber())
                .category(request.getCategory())
                .propertyType(request.getPropertyType())
                .assetDescription(request.getAssetDescription())
                .effectiveLife(request.getEffectiveLife())
                .build();
    }

    /**
     * Translates a populated internal business Domain Model back out to a client-safe Response Record.
     */
    public PropertyAssetResponse toResponse(PropertyAsset domain) {
        if (domain == null) return null;

        return new PropertyAssetResponse(
                domain.getId(),
                domain.getSectionNumber(),
                domain.getCategory(),
                domain.getPropertyType(),
                domain.getAssetDescription(),
                domain.getEffectiveLife()
        );
    }
}
