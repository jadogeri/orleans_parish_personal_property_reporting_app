package com.opao.pp_api.features.user_change.dto.request;

import com.opao.pp_api.features.user_change.validation.ValidVerificationCode;
import com.opao.pp_api.common.validation.ValidForeignId;

public record UserChangeUpdateRequest(
    
    @ValidVerificationCode.Optional 
    String verificationCode,

    @ValidForeignId.Optional 
    Integer userChangeTypeId,

    @ValidForeignId.Optional 
    Integer userId
) {}
