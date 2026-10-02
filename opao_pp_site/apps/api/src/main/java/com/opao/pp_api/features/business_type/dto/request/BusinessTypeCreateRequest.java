package com.opao.pp_api.features.business_type.dto.request;

import com.opao.pp_api.features.business_type.validation.ValidBusinessTypeCode;
import com.opao.pp_api.features.business_type.validation.ValidBusinessTypeDescription;

public record BusinessTypeCreateRequest(
    
    @ValidBusinessTypeCode Integer code,

    @ValidBusinessTypeDescription  String description
) {}
