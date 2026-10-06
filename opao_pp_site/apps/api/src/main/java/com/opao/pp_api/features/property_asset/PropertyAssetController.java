package com.opao.pp_api.features.property_asset;

import com.opao.pp_api.features.property_asset.dto.request.PropertyAssetCreateRequest;
import com.opao.pp_api.features.property_asset.dto.request.PropertyAssetUpdateRequest;
import com.opao.pp_api.features.property_asset.dto.response.PropertyAssetResponse;
import com.opao.pp_api.features.property_asset.mapper.PropertyAssetDtoMapper;
import com.opao.pp_api.features.property_asset.model.PropertyAsset;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/property-assets")
@RequiredArgsConstructor
@Tag(name = "property-assets", description = "Operations managing corporate personal property asset configurations")
public class PropertyAssetController {

    private final PropertyAssetService service;
    private final PropertyAssetDtoMapper dtoMapper;

    /**
     * POST /api/v1/property-assets
     * Provisions a brand new PropertyAsset logging record registry entry.
     */
    @PostMapping
    @Operation(summary = "Create a new property asset entry configuration")
    public ResponseEntity<PropertyAssetResponse> createAsset(@Valid @RequestBody PropertyAssetCreateRequest request) {
        PropertyAsset domainModel = dtoMapper.toDomain(request);
        PropertyAsset createdDomain = service.create(domainModel);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(dtoMapper.toResponse(createdDomain));
    }

    /**
     * PUT /api/v1/property-assets/{id}
     * Applies transactional edits or incremental payload patch variations to an active tracking resource.
     */
    @PutMapping("/{id}")
    @Operation(summary = "Update an existing property asset configuration by ID")
    public ResponseEntity<PropertyAssetResponse> updateAsset(
            @PathVariable Integer id,
            @Valid @RequestBody PropertyAssetUpdateRequest request) {
        PropertyAsset domainUpdate = dtoMapper.toDomain(id, request);
        PropertyAsset updatedDomain = service.edit(id, domainUpdate);
        return ResponseEntity.ok(dtoMapper.toResponse(updatedDomain));
    }

    /**
     * GET /api/v1/property-assets/{id}
     * Retrieves an isolated property asset definition configuration by its unique database primary identifier.
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get a single property asset configuration row entry by ID")
    public ResponseEntity<PropertyAssetResponse> getAssetById(@PathVariable Integer id) {
        return service.findPropertyAsset(id)
                .map(dtoMapper::toResponse)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * GET /api/v1/property-assets/search
     * Dynamic filtering search route covering targeted service layer dictionary queries.
     */
    @GetMapping("/search")
    @Operation(summary = "Search property asset configurations using selective single parameter criteria")
    public ResponseEntity<List<PropertyAssetResponse>> searchAssets(
            @RequestParam(required = false) String sectionNumber,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String propertyType,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) Integer effectiveLife) {

        List<PropertyAsset> results;

        if (sectionNumber != null) {
            results = service.findPropertyAssetBySectionNumber(sectionNumber);
        } else if (category != null) {
            results = service.findPropertyAssetByCategory(category);
        } else if (propertyType != null) {
            results = service.findPropertyAssetByPptype(propertyType);
        } else if (description != null) {
            results = service.findPropertyAssetByDescription(description);
        } else if (effectiveLife != null) {
            results = service.findPropertyAssetByEffectiveLife(effectiveLife);
        } else {
            results = service.findPropertyAssetEntities();
        }

        List<PropertyAssetResponse> responses = results.stream()
                .map(dtoMapper::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    /**
     * GET /api/v1/property-assets/pageable
     * Fetches a paginated sub-collection matrix layout of asset schemas.
     */
    @GetMapping("/pageable")
    @Operation(summary = "Retrieve property assets via framework-driven pagination layouts")
    public ResponseEntity<Page<PropertyAssetResponse>> getAssetsPaged(Pageable pageable) {
        Page<PropertyAssetResponse> pagedResponses = service.findPropertyAssetEntities(pageable)
                .map(dtoMapper::toResponse);
        return ResponseEntity.ok(pagedResponses);
    }

    /**
     * GET /api/v1/property-assets/count
     * Quick metric route yielding aggregate size count vectors for diagnostic dashboard matrices.
     */
    @GetMapping("/count")
    @Operation(summary = "Retrieve total system configuration row sizing metrics metrics")
    public ResponseEntity<Long> getAssetCount() {
        return ResponseEntity.ok(service.getPropertyAssetCount());
    }

    /**
     * DELETE /api/v1/property-assets/{id}
     * Drops a property metadata configuration element permanently by ID.
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Permanently purge an isolated asset structure configuration from storage tables")
    public ResponseEntity<Void> deleteAsset(@PathVariable Integer id) {
        service.destroy(id);
        return ResponseEntity.noContent().build();
    }
}
