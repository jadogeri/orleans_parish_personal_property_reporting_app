package com.opao.pp_api.features.user.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(example = "johndoe", description = "The unique login username for the user")
    private String username;

    @ValidFullName.Optional
    @JsonProperty("fullName")
    @Schema(example = "John Doe", description = "The full name of the user")
    private String fullName;      

    @ValidEmail.Optional
    @JsonProperty("emailAddress")
    @Schema(example = "johndoe@gmail.com", description = "The unique email address for the user")
    private String email;         

    @ValidPhoneNumber.Optional
    @JsonProperty("phoneNumber")
    @Schema(example = "1234567890", description = "The phone number of the user")
    private String phoneNumber;    

    @Size(min = 8, max = 255, message = "Password must be between {min} and {max} characters")
    @JsonProperty("password")
    private String clearTextPassword;

    @JsonProperty("isActive")
    @Schema(example = "true", description = "Indicates whether the user is active")
    private Boolean isActive;

    @ValidForeignId.Optional
    @JsonProperty("roleId")
    @Schema(example = "1", description = "The ID of the role assigned to the user")
    private Integer userRoleId;   

    @ValidForeignId.Optional
    @JsonProperty("statusId")
    @Schema(example = "1", description = "The ID of the status assigned to the user")
    private Integer userStatusId; 
}
