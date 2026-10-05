package com.opao.pp_api.features.noa_pp_lat5.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.LocalDate;
import java.util.List;

import com.opao.pp_api.features.business_type.dto.response.BusinessTypeResponse;
import com.opao.pp_api.features.noa_pp_lat5_filing.dto.response.NoaPpLat5FilingResponse;

// 💡 Fixed package reference to match your plural package structure:
import com.opao.pp_api.features.noa_pp_lat5_inventories.dto.response.NoaPpLat5InventoriesResponse;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record NoaPpLat5Response(
    Integer id,
    String jurisdiction,
    String parcelId,
    String alternateId,
    Integer taxYear,
    String ownerName,
    String address1,
    String address2,
    String cityName,
    String stateCode,
    String zipCode,
    String pin,
    String contactName,
    String contactPhone,
    String contactFax,
    String contactEmail,
    Boolean contactSendEmails,
    String propertyAddress,
    String taxpayerName,
    LocalDate taxpayerPreparedDate,
    String taxPreparerName,
    String taxPreparerPhone,
    String taxPreparerEmail,
    LocalDate taxPreparerPreparedDate,
    Integer formId,
    BusinessTypeResponse businessType,
    
    // ✅ Both types are now successfully recognized and imported
    List<NoaPpLat5FilingResponse> filings,
    List<NoaPpLat5InventoriesResponse> inventories
) {}
