package com.opao.pp_api.features.user.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record UserResponse(
    @JsonProperty("id")
    Integer id,
    @JsonProperty("userName")
    String username,
    @JsonProperty("fullName")
    String fullName,
    @JsonProperty("emailAddress")
    String email,
    @JsonProperty("phoneNumber")
    String phoneNumber,
    @JsonProperty("isActive")
    Boolean isActive,
    
    // 💡 Flattened fields directly mapping role and status details
    @JsonProperty("roleId")
    Integer userRoleId,
    @JsonProperty("roleName")
    String userRoleName,
    @JsonProperty("statusId")
    Integer userStatusId,
    @JsonProperty("statusName")
    String userStatusName
) {}
