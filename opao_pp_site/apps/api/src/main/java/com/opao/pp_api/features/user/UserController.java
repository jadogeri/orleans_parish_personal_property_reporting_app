package com.opao.pp_api.features.user;

import com.opao.pp_api.features.user.dto.request.UserCreateRequest;
import com.opao.pp_api.features.user.dto.request.UserUpdateRequest;
import com.opao.pp_api.features.user.dto.response.UserResponse;
import com.opao.pp_api.features.user.mapper.UserDtoMapper;
import com.opao.pp_api.features.user.model.User;
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
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Tag(name = "users", description = "Operations managing user identities, profile assignments, and authentication context states")
public class UserController {

    private final UserService userService;
    private final UserDtoMapper dtoMapper;

    /**
     * GET /api/v1/users
     * Retrieves all registered application user profiles.
     */
    @GetMapping
    @Operation(summary = "Retrieve all registered user accounts")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<UserResponse> responses = userService.getAllUsers().stream()
                .map(dtoMapper::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    /**
     * GET /api/v1/users/{id}
     * Retrieves an isolated user profile card by its primary internal ID.
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get a single user profile entry by database ID")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Integer id) {
        return userService.getUserById(id)
                .map(dtoMapper::toResponse)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * GET /api/v1/users/username/{username}
     * Looks up an individual identity record using its unique login username criterion.
     */
    @GetMapping("/username/{username}")
    @Operation(summary = "Find a user profile by their unique account login username")
    public ResponseEntity<UserResponse> getUserByUsername(@PathVariable String username) {
        return userService.getUserByUsername(username)
                .map(dtoMapper::toResponse)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * POST /api/v1/users
     * Provisions a brand new identity record profile into system storage logs.
     */
    @PostMapping
    @Operation(summary = "Provisions a brand-new user application registry account profile")
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserCreateRequest request) {
        User domainModel = dtoMapper.toDomain(request);
        User createdDomain = userService.create(domainModel);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(dtoMapper.toResponse(createdDomain));
    }

    /**
     * PUT /api/v1/users/{id}
     * Overwrites or updates patch variations selectively against a target persistent index context.
     */
    @PutMapping("/{id}")
    @Operation(summary = "Update structural parameters on an existing user account context profile")
    public ResponseEntity<UserResponse> updateUser(
            @PathVariable Integer id,
            @Valid @RequestBody UserUpdateRequest request) {
        
        User domainUpdate = dtoMapper.toDomain(id, request);
        return userService.updateUser(id, domainUpdate)
                .map(dtoMapper::toResponse)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * DELETE /api/v1/users/{id}
     * Removes an identity catalog trace ledger row permanently from memory tables.
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Permanently delete an individual user identity record")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        boolean deleted = userService.deleteUser(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
