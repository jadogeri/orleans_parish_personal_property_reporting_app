package com.opao.pp_api.features.form.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record FormResponse(
    Integer id,
    String title,
    Integer filingYear,
    LocalDateTime lastModifiedDate,
    String billNumber,
    String pin,
    Integer formTypeId,
    String statusName,
    Integer userId,
    Boolean hasLineItems
) {}
