package com.opao.pp_api.features.form;

import com.opao.pp_api.features.form.dto.request.FormCreateRequest;
import com.opao.pp_api.features.form.dto.request.FormUpdateRequest;
import com.opao.pp_api.features.form.dto.response.FormResponse;
import com.opao.pp_api.features.form.mapper.FormDtoMapper;
import com.opao.pp_api.features.form.model.Form;
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
@RequestMapping("/api/v1/forms")
@RequiredArgsConstructor
@Tag(name = "Forms", description = "Operations related to personal property filing forms")
public class FormController {

    private final FormService formService;
    private final FormDtoMapper dtoMapper;

    /**
     * POST /api/v1/forms
     * Provisions a brand new Form record in draft status.
     */
    @PostMapping
    @Operation(summary = "Create a new form record")
    public ResponseEntity<FormResponse> createForm(@Valid @RequestBody FormCreateRequest request) {
        Form domainModel = dtoMapper.toDomain(request);
        Form createdDomain = formService.create(domainModel);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(dtoMapper.toResponse(createdDomain));
    }

    /**
     * PUT /api/v1/forms/{id}
     * Applies transactional edits or updates against an existing form resource.
     */
    @PutMapping("/{id}")
    @Operation(summary = "Update an existing form record by ID")
    public ResponseEntity<FormResponse> updateForm(
            @PathVariable Integer id,
            @Valid @RequestBody FormUpdateRequest request) {
        Form domainUpdate = dtoMapper.toDomain(id, request);
        Form updatedDomain = formService.edit(id, domainUpdate);
        return ResponseEntity.ok(dtoMapper.toResponse(updatedDomain));
    }

    /**
     * GET /api/v1/forms/{id}
     * Retrieves an isolated form record metadata schema by its primary key.
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get a form record by its unique database ID")
    public ResponseEntity<FormResponse> getFormById(@PathVariable Integer id) {
        return formService.findForm(id)
                .map(dtoMapper::toResponse)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * GET /api/v1/forms/search
     * Multi-variable query endpoint to search forms dynamically via optional request parameters.
     */
    @GetMapping("/search")
    @Operation(summary = "Search forms using selective parameter criteria options")
    public ResponseEntity<List<FormResponse>> searchForms(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) Integer filingYear,
            @RequestParam(required = false) String billNumber,
            @RequestParam(required = false) String pin,
            @RequestParam(required = false) String statusName) {

        List<Form> results;

        if (filingYear != null && billNumber != null && pin != null) {
            results = formService.findByFilingYearAndBillNumberAndPin(filingYear, billNumber, pin);
        } else if (billNumber != null && pin != null && statusName != null) {
            results = formService.findByBillNumberAndPinAndStatusName(billNumber, pin, statusName);
        } else if (billNumber != null && pin != null) {
            results = formService.findByBillNumberAndPin(billNumber, pin);
        } else if (billNumber != null && filingYear != null) {
            results = formService.findByBillNumberAndFilingYear(billNumber, filingYear);
        } else if (title != null) {
            results = formService.findByTitle(title);
        } else if (filingYear != null) {
            results = formService.findByFilingYear(filingYear);
        } else if (billNumber != null) {
            results = formService.findByBillNumber(billNumber);
        } else {
            results = formService.findFormEntities();
        }

        List<FormResponse> responses = results.stream()
                .map(dtoMapper::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    /**
     * GET /api/v1/forms/pageable
     * Fetches a paginated sub-collection segment of forms using standard framework query filters.
     */
    @GetMapping("/pageable")
    @Operation(summary = "Retrieve forms via a structured pageable matrix offset configuration")
    public ResponseEntity<Page<FormResponse>> getFormsPaged(Pageable pageable) {
        Page<FormResponse> pagedResponses = formService.findFormEntities(pageable)
                .map(dtoMapper::toResponse);
        return ResponseEntity.ok(pagedResponses);
    }

    /**
     * DELETE /api/v1/forms/{id}
     * Purges a targeted form log permanently out of records.
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Permanently purge a form record row from storage logs")
    public ResponseEntity<Void> deleteForm(@PathVariable Integer id) {
        formService.destroy(id);
        return ResponseEntity.noContent().build();
    }
}
