package com.opao.pp_api.features.noa_pp_lat5_inventories.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.opao.pp_api.common.validation.ValidJurisdiction;
import com.opao.pp_api.common.validation.ValidParcelAddress;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NoaPpLat5InventoriesUpdateRequest {

    @NotNull(message = "Inventory ID is required for updates")
    private Integer id;

    @ValidJurisdiction.Optional
    private String jurisdiction;

    @ValidParcelAddress.Optional
    private String parcelAddress;

    private Integer taxYear;

    private Integer filingYear;

    private String inventoryType;

    private Integer inventoryMonth;

    private Long inventoryAmount;
}
