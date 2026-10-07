package com.opao.pp_api.features.user.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.opao.pp_api.common.validation.ValidUsername;
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
    @JsonProperty("userName")
    private String username;

    @ValidFullName
    @JsonProperty("fullName")
    private String fullName;      

    @ValidEmail
    @JsonProperty("emailAddress")
    private String email;         

    @ValidPhoneNumber
    @JsonProperty("phoneNumber")
    private String phoneNumber;    

    @NotBlank(message = "Password cannot be blank")
    @Size(min = 8, max = 255, message = "Password must be between {min} and {max} characters")
    @JsonProperty("password")
    private String clearTextPassword;

    @NotNull(message = "User role reference is required")
    @ValidForeignId
    @JsonProperty("roleId")
    private Integer userRoleId;   

    @NotNull(message = "User status reference is required")
    @ValidForeignId
    @JsonProperty("statusId")
    private Integer userStatusId; 
}
