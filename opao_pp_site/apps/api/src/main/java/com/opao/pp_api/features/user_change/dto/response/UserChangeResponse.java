package com.opao.pp_api.features.user_change.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL) 
public record UserChangeResponse(
    Integer id,
    String verificationCode,               
    LocalDateTime initiatedTime,
    Integer userChangeTypeId,
    Integer userId
){} 