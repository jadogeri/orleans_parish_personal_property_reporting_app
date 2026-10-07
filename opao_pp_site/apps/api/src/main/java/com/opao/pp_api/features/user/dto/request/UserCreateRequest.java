package com.opao.pp_api.features.user.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import com.opao.pp_api.common.validation.ValidPassword;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.opao.pp_api.common.validation.ValidUsername;

import io.swagger.v3.oas.annotations.media.Schema;

import com.opao.pp_api.common.validation.ValidFullName;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.opao.pp_api.common.validation.ValidEmail;
import com.opao.pp_api.common.validation.ValidPhoneNumber;
import com.opao.pp_api.common.validation.ValidForeignId;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateRequest {

    @ValidUsername
    @Schema(example = "johndoe", description = "The unique login username for the user")
    @JsonProperty("userName")
    private String username;

    @ValidFullName
    @Schema(example = "John Doe", description = "The full name of the user")
    @JsonProperty("fullName")
    private String fullName;      

    @ValidEmail
    @Schema(example = "johndoe@gmail.com", description = "The unique email address for the user") 
    @JsonProperty("emailAddress")
    private String email;         

    @ValidPhoneNumber
    @Schema(example = "1234567890", description = "The phone number of the user")
    @JsonProperty("phoneNumber")
    private String phoneNumber;    

    
    @ValidPassword
    @NotBlank(message = "Password cannot be blank")
    @Schema(example = "P@ssw0rd123", description = "The password for the user")
    @JsonProperty("password")
    private String clearTextPassword;

    @NotNull(message = "User role reference is required")
    @Schema(example = "1", description = "The ID of the role assigned to the user")
    @ValidForeignId
    @JsonProperty("roleId")
    private Integer userRoleId;   

    @NotNull(message = "User status reference is required")
    @Schema(example = "1", description = "The ID of the status assigned to the user")
    @ValidForeignId
    @JsonProperty("statusId")
    private Integer userStatusId; 
}
