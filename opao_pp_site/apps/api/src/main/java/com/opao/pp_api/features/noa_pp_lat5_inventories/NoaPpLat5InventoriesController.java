package com.opao.pp_api.features.noa_pp_lat5_inventories;

import com.opao.pp_api.features.noa_pp_lat5_inventories.dto.request.NoaPpLat5InventoriesCreateRequest;
import com.opao.pp_api.features.noa_pp_lat5_inventories.dto.request.NoaPpLat5InventoriesUpdateRequest;
import com.opao.pp_api.features.noa_pp_lat5_inventories.dto.response.NoaPpLat5InventoriesResponse;
import com.opao.pp_api.features.noa_pp_lat5_inventories.mapper.NoaPpLat5InventoriesDtoMapper;
import com.opao.pp_api.features.noa_pp_lat5_inventories.model.NoaPpLat5Inventories;
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
@RequestMapping("/api/v1/noa-pp-lat5-inventories")
@RequiredArgsConstructor
@Tag(name = "Inventories", description = "Operations managing inventory log records on LAT5 personal property filings")
public class NoaPpLat5InventoriesController {

    private final NoaPpLat5InventoriesService service;
    private final NoaPpLat5InventoriesDtoMapper dtoMapper;

    /**
     * GET /api/v1/noa-pp-lat5-inventories
     * Retrieves all inventory detail log entries registered in the system.
     */
    @GetMapping
    @Operation(summary = "Retrieve all inventory line records")
    public ResponseEntity<List<NoaPpLat5InventoriesResponse>> getAllInventories() {
        List<NoaPpLat5InventoriesResponse> responses = service.findAll().stream()
                .map(dtoMapper::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    /**
     * GET /api/v1/noa-pp-lat5-inventories/{id}
     * Retrieves a single inventory entry row context by its primary key identifier.
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get an individual inventory record by database ID")
    public ResponseEntity<NoaPpLat5InventoriesResponse> getInventoryById(@PathVariable Integer id) {
        return service.findById(id)
                .map(dtoMapper::toResponse)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * GET /api/v1/noa-pp-lat5-inventories/search
     * Dynamic filtering search route mapping optional request metrics into domain search logic blocks.
     */
    @GetMapping("/search")
    @Operation(summary = "Search inventory logs using optional criteria variations")
    public ResponseEntity<List<NoaPpLat5InventoriesResponse>> searchInventories(
            @RequestParam(required = false) String jurisdiction,
            @RequestParam(required = false) String parcelId,
            @RequestParam(required = false) Integer taxYear,
            @RequestParam(required = false) String inventoryType,
            @RequestParam(required = false) String inventoryMonth) {

        List<NoaPpLat5Inventories> results;

        if (jurisdiction != null) {
            results = service.findByJurisdiction(jurisdiction);
        } else if (parcelId != null) {
            results = service.findByParcelId(parcelId);
        } else if (taxYear != null) {
            results = service.findByTaxYear(taxYear);
        } else if (inventoryType != null) {
            results = service.findByInventoryType(inventoryType);
        } else if (inventoryMonth != null) {
            results = service.findByInventoryMonth(inventoryMonth);
        } else {
            results = service.findAll();
        }

        List<NoaPpLat5InventoriesResponse> responses = results.stream()
                .map(dtoMapper::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    /**
     * POST /api/v1/noa-pp-lat5-inventories
     * Persists a pristine inventory entry line record.
     */
    @PostMapping
    @Operation(summary = "Provisions a brand-new inventory detail line entry")
    public ResponseEntity<NoaPpLat5InventoriesResponse> createInventory(
            @Valid @RequestBody NoaPpLat5InventoriesCreateRequest request) {
        
        NoaPpLat5Inventories domainModel = dtoMapper.toDomain(request);
        NoaPpLat5Inventories createdDomain = service.create(domainModel);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(dtoMapper.toResponse(createdDomain));
    }

    /**
     * PUT /api/v1/noa-pp-lat5-inventories/{id}
     * Applies structural updates or fields selectively down to existing data records.
     */
    @PutMapping("/{id}")
    @Operation(summary = "Update an existing inventory record entry by ID")
    public ResponseEntity<NoaPpLat5InventoriesResponse> updateInventory(
            @PathVariable Integer id,
            @Valid @RequestBody NoaPpLat5InventoriesUpdateRequest request) {
        
        NoaPpLat5Inventories domainUpdate = dtoMapper.toDomain(id, request);
        return service.update(id, domainUpdate)
                .map(dtoMapper::toResponse)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * DELETE /api/v1/noa-pp-lat5-inventories/{id}
     * Purges an obsolete inventory asset track segment permanently by primary key.
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Permanently purge an individual inventory log statement entry")
    public ResponseEntity<Void> deleteInventory(@PathVariable Integer id) {
        boolean deleted = service.deleteById(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
