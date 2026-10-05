package com.opao.pp_api.features.noa_pp_lat5_inventories.dto.request;

import jakarta.validation.constraints.NotBlank;
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
public class NoaPpLat5InventoriesCreateRequest {

    @ValidJurisdiction
    private String jurisdiction;

    @ValidParcelAddress
    private String parcelAddress;

    @NotNull(message = "Tax year is required")
    private Integer taxYear;

    @NotNull(message = "Filing year is required")
    private Integer filingYear;

    @NotBlank(message = "Inventory type cannot be blank")
    private String inventoryType;

    private Integer inventoryMonth;

    @NotNull(message = "Inventory amount is required")
    private Long inventoryAmount;
}
