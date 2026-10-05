package com.opao.pp_api.features.noa_pp_lat5_filing.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record NoaPpLat5FilingResponse(
    Integer id,
    String jurisdiction,
    String parcelId,
    Integer taxYear,
    String category,
    String propertyType,
    Integer filingYear,
    Integer yearAcquired,
    Integer noOfUnits,
    Long acquisitionCost,
    Integer effectiveLife,
    String consignerOwnerName,
    String consignerMailingAddr,
    Long consignerRentalAmt,
    String itemDescription,
    String consignerTelNo,
    Integer noaPpLat5Id
) {}
