package com.opao.pp_api.features.user.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;  

@JsonInclude(JsonInclude.Include.NON_NULL)
public record UserResponse(
    @JsonProperty("id")
    @Schema(example = "1", description = "The unique ID of the user") 
    Integer id,
    @Schema(example = "johndoe", description = "The unique login username for the user") 
    @JsonProperty("userName")
    String username,
    @Schema(example = "John Doe", description = "The full name of the user") 
    @JsonProperty("fullName")
    String fullName,
    @Schema(example = "johndoe@gmail.com", description = "The unique email address for the user") 
    @JsonProperty("emailAddress")
    String email,
    @Schema(example = "1234567890", description = "The phone number of the user") 
    @JsonProperty("phoneNumber")
    String phoneNumber,
    @Schema(example = "true", description = "Indicates whether the user is active") 
    @JsonProperty("isActive")
    Boolean isActive,
    
    // 💡 Flattened fields directly mapping role and status details
    @JsonProperty("roleId")
    Integer userRoleId,
    @Schema(example = "Admin", description = "The name of the role assigned to the user") 
    @JsonProperty("roleName")
    String userRoleName,
    @Schema(example = "1", description = "The ID of the status assigned to the user") 
    @JsonProperty("statusId")
    Integer userStatusId,
    @Schema(example = "Active", description = "The name of the status assigned to the user") 
    @JsonProperty("statusName")
    String userStatusName
) {}
