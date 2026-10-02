package com.opao.pp_api.features.user_change.dto.request;

import com.opao.pp_api.features.user_change.validation.ValidVerificationCode;
import com.opao.pp_api.common.validation.ValidForeignId;

public record UserChangeCreateRequest(
    
    @ValidVerificationCode 
    String verificationCode,

    @ValidForeignId 
    Integer userChangeTypeId,

    @ValidForeignId 
    Integer userId
) {}
