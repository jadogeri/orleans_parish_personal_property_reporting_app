package com.opao.pp_api.features.business_type;

import com.opao.pp_api.features.business_type.dto.request.BusinessTypeCreateRequest;
import com.opao.pp_api.features.business_type.dto.request.BusinessTypeUpdateRequest;
import com.opao.pp_api.features.business_type.dto.response.BusinessTypeResponse;
import com.opao.pp_api.features.business_type.mapper.BusinessTypeDtoMapper;
import com.opao.pp_api.features.business_type.model.BusinessType;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/business-types")
@RequiredArgsConstructor
@Tag(name = "BusinessTypes", description = "Operations related to personal property business types")
public class BusinessTypeController {

    private final BusinessTypeService businessTypeService;
    private final BusinessTypeDtoMapper dtoMapper;

    /**
     * GET /api/v1/business-types
     * Retrieves all business types registered in the system.
     */
    @GetMapping
    public ResponseEntity<List<BusinessTypeResponse>> getAllBusinessTypes() {
        List<BusinessTypeResponse> responses = businessTypeService.getAllBusinessTypes().stream()
                .map(dtoMapper::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    /**
     * GET /api/v1/business-types/{id}
     * Retrieves a specific business type by its identifier.
     */
    @GetMapping("/{id}")
    public ResponseEntity<BusinessTypeResponse> getBusinessTypeById(@PathVariable Integer id) {
        return businessTypeService.getBusinessTypeById(id)
                .map(dtoMapper::toResponse)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * POST /api/v1/business-types
     * Provisions a brand new business type registration code.
     */
    @PostMapping
    public ResponseEntity<BusinessTypeResponse> createBusinessType(
            @Valid @RequestBody BusinessTypeCreateRequest request) {
        
        BusinessType domainModel = dtoMapper.toDomain(request);
        BusinessType createdDomain = businessTypeService.createBusinessType(domainModel);
        
        // FIXED: Corrected functional method mapping invocation syntax
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(dtoMapper.toResponse(createdDomain));
    }


    /**
     * PUT /api/v1/business-types/{id}
     * Performs a partial or structural field variation update against an existing record.
     */
    @PutMapping("/{id}")
    public ResponseEntity<BusinessTypeResponse> updateBusinessType(
            @PathVariable Integer id,
            @Valid @RequestBody BusinessTypeUpdateRequest request) {
        
        BusinessType domainUpdate = dtoMapper.toDomain(id, request);
        return businessTypeService.updateBusinessType(id, domainUpdate)
                .map(dtoMapper::toResponse)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * DELETE /api/v1/business-types/{id}
     * Purges a target configuration metadata row if no referential tracking bindings conflict.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBusinessType(@PathVariable Integer id) {
        boolean deleted = businessTypeService.deleteBusinessType(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
