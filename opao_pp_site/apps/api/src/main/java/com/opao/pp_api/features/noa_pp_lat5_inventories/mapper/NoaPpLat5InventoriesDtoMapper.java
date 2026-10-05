package com.opao.pp_api.features.noa_pp_lat5_inventories.mapper;

import org.springframework.stereotype.Component;
import com.opao.pp_api.features.noa_pp_lat5_inventories.dto.request.NoaPpLat5InventoriesCreateRequest;
import com.opao.pp_api.features.noa_pp_lat5_inventories.dto.request.NoaPpLat5InventoriesUpdateRequest;
import com.opao.pp_api.features.noa_pp_lat5_inventories.dto.response.NoaPpLat5InventoriesResponse;
import com.opao.pp_api.features.noa_pp_lat5_inventories.model.NoaPpLat5Inventories;

@Component
public class NoaPpLat5InventoriesDtoMapper {

    /**
     * Maps an incoming inventory creation payload into a clean Domain Model instance.
     * Maps the incoming payload's 'parcelAddress' string neatly into your domain's 'parcelId' field.
     */
    public NoaPpLat5Inventories toDomain(NoaPpLat5InventoriesCreateRequest request) {
        if (request == null) return null;

        return NoaPpLat5Inventories.builder()
                .jurisdiction(request.getJurisdiction())
                .parcelId(request.getParcelAddress()) // 💡 Bridges request field mapping mismatch cleanly
                .taxYear(request.getTaxYear() != null ? request.getTaxYear() : 0)
                .filingYear(request.getFilingYear() != null ? request.getFilingYear() : 0)
                .inventoryType(request.getInventoryType())
                .inventoryMonth(request.getInventoryMonth())
                .inventoryAmount(request.getInventoryAmount())
                .build();
    }

    /**
     * Maps an explicit URL PathVariable resource parameter identifier alongside an update request payload block.
     * Unassigned payload elements safely pass as null so the service logic can apply selective field patching.
     */
    public NoaPpLat5Inventories toDomain(Integer id, NoaPpLat5InventoriesUpdateRequest request) {
        if (request == null) return null;

        return NoaPpLat5Inventories.builder()
                .id(id)
                .jurisdiction(request.getJurisdiction())
                .parcelId(request.getParcelAddress()) // 💡 Bridges request field mapping mismatch cleanly
                .taxYear(request.getTaxYear())
                .filingYear(request.getFilingYear())
                .inventoryType(request.getInventoryType())
                .inventoryMonth(request.getInventoryMonth())
                .inventoryAmount(request.getInventoryAmount())
                .build();
    }

    /**
     * Translates an un-marshaled internal business Domain Model instance back out to an immutable client-safe Response Record.
     */
    public NoaPpLat5InventoriesResponse toResponse(NoaPpLat5Inventories domain) {
        if (domain == null) return null;

        return new NoaPpLat5InventoriesResponse(
                domain.getId(),
                domain.getJurisdiction(),
                domain.getParcelId(),
                domain.getTaxYear(),
                domain.getFilingYear(),
                domain.getInventoryType(),
                domain.getInventoryMonth(),
                domain.getInventoryAmount()
        );
    }
}
