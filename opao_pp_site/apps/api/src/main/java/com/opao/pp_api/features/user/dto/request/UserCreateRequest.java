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
    private String username;

    @ValidFullName
    private String fullName;      

    @ValidEmail
    private String email;         

    @ValidPhoneNumber
    private String phoneNumber;    

    @NotBlank(message = "Password cannot be blank")
    @Size(min = 8, max = 255, message = "Password must be between {min} and {max} characters")
    private String clearTextPassword;

    @NotNull(message = "User role reference is required")
    @ValidForeignId
    private Integer userRoleId;   

    @NotNull(message = "User status reference is required")
    @ValidForeignId
    private Integer userStatusId; 
}
