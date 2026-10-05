package com.opao.pp_api.features.noa_pp_lat5_inventories.dto.response;    

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record NoaPpLat5InventoriesResponse(
    Integer id,
    String jurisdiction,
    String parcelId,
    Integer taxYear,
    Integer filingYear,
    String inventoryType,
    Integer inventoryMonth,
    Long inventoryAmount
) {}
