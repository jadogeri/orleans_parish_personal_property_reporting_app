package com.opao.pp_api.features.business_type.dto.request;

import com.opao.pp_api.features.business_type.validation.ValidBusinessTypeCode;
import com.opao.pp_api.features.business_type.validation.ValidBusinessTypeDescription;

public record BusinessTypeUpdateRequest(

    @ValidBusinessTypeCode.Optional Integer code,

    @ValidBusinessTypeDescription.Optional String description
) {}
