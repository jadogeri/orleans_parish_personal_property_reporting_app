package com.opao.pp_api.features.user_change;

import com.opao.pp_api.features.user_change.dto.request.UserChangeCreateRequest;
import com.opao.pp_api.features.user_change.dto.response.UserChangeResponse;
import com.opao.pp_api.features.user_change.mapper.UserChangeDtoMapper;
import com.opao.pp_api.features.user_change.model.UserChange;
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
@RequestMapping("/api/v1/user-changes")
@RequiredArgsConstructor
@Tag(name = "user-changes", description = "Operations tracking system identity updates, password resets, and verification audit trails")
public class UserChangeController {

    private final UserChangeService service;
    private final UserChangeDtoMapper dtoMapper;

    /**
     * POST /api/v1/user-changes
     * Provisions a brand-new user change verification request.
     */
    @PostMapping
    @Operation(summary = "Create a new user modification log change tracking event")
    public ResponseEntity<UserChangeResponse> createUserChange(@Valid @RequestBody UserChangeCreateRequest request) {
        UserChange domainModel = dtoMapper.toDomain(request);
        UserChange createdDomain = service.create(domainModel);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(dtoMapper.toResponse(createdDomain));
    }

    /**
     * GET /api/v1/user-changes/{id}
     * Retrieves an isolated change record tracking event card by its primary table key.
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get an individual user change tracking event by ID")
    public ResponseEntity<UserChangeResponse> getUserChangeById(@PathVariable Integer id) {
        return service.findUserChange(id)
                .map(dtoMapper::toResponse)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * GET /api/v1/user-changes/verification/{verificationCode}
     * Looks up an individual identity delta token verification lifecycle using its raw hash code string.
     */
    @GetMapping("/verification/{verificationCode}")
    @Operation(summary = "Look up a user modification tracking state using its unique verification token key string")
    public ResponseEntity<UserChangeResponse> getUserChangeByVerificationCode(@PathVariable String verificationCode) {
        return service.findUserChangeByVerificationCode(verificationCode)
                .map(dtoMapper::toResponse)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * GET /api/v1/user-changes/pageable
     * Fetches a paginated sub-collection matrix grid layout segment over standard offset arrays.
     */
    @GetMapping("/pageable")
    @Operation(summary = "Retrieve user changes ledger entries via framework-driven pagination offsets")
    public ResponseEntity<Page<UserChangeResponse>> getUserChangesPaged(Pageable pageable) {
        Page<UserChangeResponse> pagedResponses = service.findUserChangeEntities(pageable)
                .map(dtoMapper::toResponse);
        return ResponseEntity.ok(pagedResponses);
    }

    /**
     * GET /api/v1/user-changes
     * Retrieves all user change historical logging entries tracked by storage sub-systems.
     */
    @GetMapping
    @Operation(summary = "Retrieve a comprehensive un-paged ledger of all historical change tracking entries")
    public ResponseEntity<List<UserChangeResponse>> getAllUserChanges() {
        List<UserChangeResponse> responses = service.findUserChangeEntities().stream()
                .map(dtoMapper::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    /**
     * GET /api/v1/user-changes/count
     * Yields analytical sizing dimensions metrics tracking aggregate database ledger capacity vectors.
     */
    @GetMapping("/count")
    @Operation(summary = "Retrieve aggregate historical processing track metric row counts")
    public ResponseEntity<Long> getUserChangeCount() {
        return ResponseEntity.ok(service.getUserChangeCount());
    }

    /**
     * DELETE /api/v1/user-changes/{id}
     * Purges or invalidates a tracked change log registry event context segment permanently out of tables.
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Permanently purge an individual user modification event log row from lookup indices")
    public ResponseEntity<Void> deleteUserChange(@PathVariable Integer id) {
        service.destroy(id);
        return ResponseEntity.noContent().build();
    }
}
