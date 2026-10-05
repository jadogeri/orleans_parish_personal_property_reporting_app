package com.opao.pp_api.features.user.dto.request;

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

    @NotNull(message = "User ID is required for updates")
    private Integer id;

    @ValidUsername.Optional
    private String username;

    @ValidFullName.Optional
    private String fullName;      

    @ValidEmail.Optional
    private String email;         

    @ValidPhoneNumber.Optional
    private String phoneNumber;    

    @Size(min = 8, max = 255, message = "Password must be between {min} and {max} characters")
    private String clearTextPassword;

    private Boolean isActive;

    @ValidForeignId.Optional
    private Integer userRoleId;   

    @ValidForeignId.Optional
    private Integer userStatusId; 
}
