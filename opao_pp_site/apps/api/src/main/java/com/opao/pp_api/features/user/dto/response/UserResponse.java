package com.opao.pp_api.features.user.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record UserResponse(
    Integer id,
    String username,
    String fullName,
    String email,
    String phoneNumber,
    Boolean isActive,
    
    // 💡 Flattened fields directly mapping role and status details
    Integer userRoleId,
    String userRoleName,
    Integer userStatusId,
    String userStatusName
) {}
