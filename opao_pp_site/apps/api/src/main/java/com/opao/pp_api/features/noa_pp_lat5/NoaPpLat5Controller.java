package com.opao.pp_api.features.noa_pp_lat5;

import com.opao.pp_api.features.noa_pp_lat5.dto.request.NoaPpLat5CreateRequest;
import com.opao.pp_api.features.noa_pp_lat5.dto.request.NoaPpLat5UpdateRequest;
import com.opao.pp_api.features.noa_pp_lat5.dto.response.NoaPpLat5Response;
import com.opao.pp_api.features.noa_pp_lat5.mapper.NoaPpLat5DtoMapper;
import com.opao.pp_api.features.noa_pp_lat5.model.NoaPpLat5;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/noa-pp-lat5")
@RequiredArgsConstructor
@Tag(name = "LAT5", description = "Operations managing Lat5 Personal Property tax filings")
public class NoaPpLat5Controller {

    private final NoaPpLat5Service service;
    private final NoaPpLat5DtoMapper dtoMapper;

    /**
     * GET /api/v1/noa-pp-lat5
     * Fetches all LAT5 form records registered in the system.
     */
    @GetMapping
    @Operation(summary = "Retrieve all LAT5 filing records")
    public ResponseEntity<List<NoaPpLat5Response>> getAllRecords() {
        List<NoaPpLat5Response> responses = service.findAll().stream()
                .map(dtoMapper::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    /**
     * GET /api/v1/noa-pp-lat5/{id}
     * Retrieves a single LAT5 record by its unique database primary key identifier.
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get a single LAT5 filing record by database ID")
    public ResponseEntity<NoaPpLat5Response> getRecordById(@PathVariable Integer id) {
        return service.findById(id)
                .map(dtoMapper::toResponse)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * GET /api/v1/noa-pp-lat5/search
     * Dynamic filtering search route covering specific domain repository lookup criteria.
     */
    @GetMapping("/search")
    @Operation(summary = "Search LAT5 records using dynamic optional criteria")
    public ResponseEntity<List<NoaPpLat5Response>> searchRecords(
            @RequestParam(required = false) String jurisdiction,
            @RequestParam(required = false) String parcelId,
            @RequestParam(required = false) Integer taxYear,
            @RequestParam(required = false) Integer businessCode,
            @RequestParam(required = false) String ownerName) {

        List<NoaPpLat5> results;

        if (jurisdiction != null) {
            results = service.findByJurisdiction(jurisdiction);
        } else if (parcelId != null) {
            results = service.findByParcelId(parcelId);
        } else if (taxYear != null) {
            results = service.findByTaxYear(taxYear);
        } else if (businessCode != null) {
            results = service.findByBusinessCode(businessCode);
        } else if (ownerName != null) {
            results = service.findByOwnerName(ownerName);
        } else {
            results = service.findAll();
        }

        List<NoaPpLat5Response> responses = results.stream()
                .map(dtoMapper::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    /**
     * POST /api/v1/noa-pp-lat5
     * Saves a pristine new LAT5 filing form baseline instance to database infrastructure.
     */
    @PostMapping
    @Operation(summary = "Create a new LAT5 filing form")
    public ResponseEntity<NoaPpLat5Response> createRecord(@Valid @RequestBody NoaPpLat5CreateRequest request) {
        NoaPpLat5 domainModel = dtoMapper.toDomain(request);
        NoaPpLat5 createdDomain = service.create(domainModel);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(dtoMapper.toResponse(createdDomain));
    }

    /**
     * PUT /api/v1/noa-pp-lat5/{id}
     * Applies partial tracking fields or updates structural entries safely against an active resource.
     */
    @PutMapping("/{id}")
    @Operation(summary = "Update an existing LAT5 filing record by ID")
    public ResponseEntity<NoaPpLat5Response> updateRecord(
            @PathVariable Integer id,
            @Valid @RequestBody NoaPpLat5UpdateRequest request) {
        
        NoaPpLat5 domainUpdate = dtoMapper.toDomain(id, request);
        return service.update(id, domainUpdate)
                .map(dtoMapper::toResponse)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * DELETE /api/v1/noa-pp-lat5/{id}
     * Clears out an unneeded or historical LAT5 record entry by primary key.
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an existing LAT5 filing record permanently")
    public ResponseEntity<Void> deleteRecord(@PathVariable Integer id) {
        boolean deleted = service.deleteById(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
