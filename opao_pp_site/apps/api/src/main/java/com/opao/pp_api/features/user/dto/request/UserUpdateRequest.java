package com.opao.pp_api.features.user.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.opao.pp_api.common.validation.ValidUsername;
import com.opao.pp_api.common.validation.ValidFullName;
import com.opao.pp_api.common.validation.ValidEmail;
import com.opao.pp_api.common.validation.ValidPhoneNumber;
import com.opao.pp_api.common.validation.ValidForeignId;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateRequest {

    @ValidUsername.Optional
    @JsonProperty("userName")
    private String username;

    @ValidFullName.Optional
    @JsonProperty("fullName")
    private String fullName;      

    @ValidEmail.Optional
    @JsonProperty("emailAddress")
    private String email;         

    @ValidPhoneNumber.Optional
    @JsonProperty("phoneNumber")
    private String phoneNumber;    

    @Size(min = 8, max = 255, message = "Password must be between {min} and {max} characters")
    @JsonProperty("password")
    private String clearTextPassword;

    @JsonProperty("isActive")
    private Boolean isActive;

    @ValidForeignId.Optional
    @JsonProperty("roleId")
    private Integer userRoleId;   

    @ValidForeignId.Optional
    @JsonProperty("statusId")
    private Integer userStatusId; 
}
