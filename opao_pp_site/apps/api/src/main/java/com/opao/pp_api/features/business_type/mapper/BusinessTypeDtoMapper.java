package com.opao.pp_api.features.business_type.mapper;

import org.springframework.stereotype.Component;
import com.opao.pp_api.features.business_type.dto.request.BusinessTypeCreateRequest;
import com.opao.pp_api.features.business_type.dto.request.BusinessTypeUpdateRequest;
import com.opao.pp_api.features.business_type.dto.response.BusinessTypeResponse;
import com.opao.pp_api.features.business_type.model.BusinessType;

@Component
public class BusinessTypeDtoMapper {

    public BusinessType toDomain(BusinessTypeCreateRequest request) {
        if (request == null) return null;
        
        return BusinessType.builder()
                .code(request.code())
                .description(request.description())
                .build();
    }

    public BusinessType toDomain(Integer id, BusinessTypeUpdateRequest request) {
        if (request == null) return null;
        
        return BusinessType.builder()
                .id(id)
                .code(request.code())
                .description(request.description())
                .build();
    }

    public BusinessTypeResponse toResponse(BusinessType domain) {
        if (domain == null) return null;
        
        return new BusinessTypeResponse(
            domain.getId(),
            domain.getCode(),
            domain.getDescription()
        );
    }
}
