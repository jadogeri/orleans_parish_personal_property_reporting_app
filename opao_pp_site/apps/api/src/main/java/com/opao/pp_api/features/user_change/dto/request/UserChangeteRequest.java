package com.opao.pp_api.features.user_change.dto.request;

import com.opao.pp_api.features.user_change.validation.ValidVerificationCode;
import com.opao.pp_api.common.validation.ValidForeignId;
import com.fasterxml.jackson.annotation.JsonProperty;

public record UserChangeteRequest(
    
    @JsonProperty("verificationCode")
    @ValidVerificationCode 
    String verificationCode,

    @JsonProperty("userChangeTypeId")
    @ValidForeignId 
    Integer userChangeTypeId,

    @JsonProperty("userId")
    @ValidForeignId 
    Integer userId
) {}
