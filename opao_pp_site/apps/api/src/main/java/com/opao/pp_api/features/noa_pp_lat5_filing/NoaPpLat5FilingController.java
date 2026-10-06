package com.opao.pp_api.features.noa_pp_lat5_filing;

import com.opao.pp_api.features.noa_pp_lat5_filing.dto.request.NoaPpLat5FilingCreateRequest;
import com.opao.pp_api.features.noa_pp_lat5_filing.dto.request.NoaPpLat5FilingUpdateRequest;
import com.opao.pp_api.features.noa_pp_lat5_filing.dto.response.NoaPpLat5FilingResponse;
import com.opao.pp_api.features.noa_pp_lat5_filing.mapper.NoaPpLat5FilingDtoMapper;
import com.opao.pp_api.features.noa_pp_lat5_filing.model.NoaPpLat5Filing;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/noa-pp-lat5-filings")
@RequiredArgsConstructor
@Tag(name = "Filings", description = "Operations managing sub-filing line item entries on LAT5 forms")
public class NoaPpLat5FilingController {

    private final NoaPpLat5FilingService service;
    private final NoaPpLat5FilingDtoMapper dtoMapper;

    /**
     * GET /api/v1/noa-pp-lat5-filings
     * Retrieves all LAT5 filing line entries registered across the system.
     */
    @GetMapping
    @Operation(summary = "Retrieve all sub-filing lines")
    public ResponseEntity<List<NoaPpLat5FilingResponse>> getAllFilings() {
        List<NoaPpLat5FilingResponse> responses = service.findAll().stream()
                .map(dtoMapper::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    /**
     * GET /api/v1/noa-pp-lat5-filings/{id}
     * Retrieves an isolated sub-filing record by its specific primary key.
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get an individual sub-filing record entry by ID")
    public ResponseEntity<NoaPpLat5FilingResponse> getFilingById(@PathVariable Integer id) {
        return service.findById(id)
                .map(dtoMapper::toResponse)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * GET /api/v1/noa-pp-lat5-filings/search
     * Dynamic filtering search endpoint mapping selective parameters straight into backend service variants.
     */
    @GetMapping("/search")
    @Operation(summary = "Search asset sub-filings using selective multi-variable filters")
    public ResponseEntity<List<NoaPpLat5FilingResponse>> searchFilings(
            @RequestParam(required = false) String jurisdiction,
            @RequestParam(required = false) String parcelId,
            @RequestParam(required = false) Integer taxYear,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String propertyType,
            @RequestParam(required = false) BigDecimal acquisitionCost) {

        List<NoaPpLat5Filing> results;

        if (jurisdiction != null) {
            results = service.findByJurisdiction(jurisdiction);
        } else if (parcelId != null) {
            results = service.findByParcelId(parcelId);
        } else if (taxYear != null) {
            results = service.findByTaxYear(taxYear);
        } else if (category != null) {
            results = service.findByCategory(category);
        } else if (propertyType != null) {
            results = service.findByPropertyType(propertyType);
        } else if (acquisitionCost != null) {
            results = service.findByAcquisitionCost(acquisitionCost);
        } else {
            results = service.findAll();
        }

        List<NoaPpLat5FilingResponse> responses = results.stream()
                .map(dtoMapper::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    /**
     * POST /api/v1/noa-pp-lat5-filings
     * Persists a pristine asset sub-filing detail entry record.
     */
    @PostMapping
    @Operation(summary = "Provisions a brand-new asset line registration details filing")
    public ResponseEntity<NoaPpLat5FilingResponse> createFiling(@Valid @RequestBody NoaPpLat5FilingCreateRequest request) {
        NoaPpLat5Filing domainModel = dtoMapper.toDomain(request);
        NoaPpLat5Filing createdDomain = service.create(domainModel);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(dtoMapper.toResponse(createdDomain));
    }

    /**
     * PUT /api/v1/noa-pp-lat5-filings/{id}
     * Overwrites or updates patch variations selectively down into target entity persistence context layers.
     */
    @PutMapping("/{id}")
    @Operation(summary = "Update an existing asset sub-filing entry by ID")
    public ResponseEntity<NoaPpLat5FilingResponse> updateFiling(
            @PathVariable Integer id,
            @Valid @RequestBody NoaPpLat5FilingUpdateRequest request) {
        
        NoaPpLat5Filing domainUpdate = dtoMapper.toDomain(id, request);
        return service.update(id, domainUpdate)
                .map(dtoMapper::toResponse)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * DELETE /api/v1/noa-pp-lat5-filings/{id}
     * Drops an unneeded or misaligned sub-filing ledger log line permanently by ID.
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Permanently purge an individual asset sub-filing line record entry row")
    public ResponseEntity<Void> deleteFiling(@PathVariable Integer id) {
        boolean deleted = service.deleteById(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
