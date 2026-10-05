package com.opao.pp_api.features.noa_pp_lat5_filing.mapper;

import org.springframework.stereotype.Component;
import com.opao.pp_api.features.noa_pp_lat5_filing.dto.request.NoaPpLat5FilingCreateRequest;
import com.opao.pp_api.features.noa_pp_lat5_filing.dto.request.NoaPpLat5FilingUpdateRequest;
import com.opao.pp_api.features.noa_pp_lat5_filing.dto.response.NoaPpLat5FilingResponse;
import com.opao.pp_api.features.noa_pp_lat5_filing.model.NoaPpLat5Filing;

@Component
public class NoaPpLat5FilingDtoMapper {

    /**
     * Maps a creation request payload into a clean NoaPpLat5Filing Domain Model instance.
     * Primitives default safely to 0 if a null bounding check slips through initialization.
     */
    public NoaPpLat5Filing toDomain(NoaPpLat5FilingCreateRequest request) {
        if (request == null) return null;

        return NoaPpLat5Filing.builder()
                .jurisdiction(request.getJurisdiction())
                .parcelId(request.getParcelId())
                .taxYear(request.getTaxYear() != null ? request.getTaxYear() : 0)
                .category(request.getCategory())
                .propertyType(request.getPropertyType())
                .filingYear(request.getFilingYear() != null ? request.getFilingYear() : 0)
                .yearAcquired(request.getYearAcquired())
                .noOfUnits(request.getNoOfUnits())
                .acquisitionCost(request.getAcquisitionCost())
                .effectiveLife(request.getEffectiveLife())
                .consignerOwnerName(request.getConsignerOwnerName())
                .consignerMailingAddr(request.getConsignerMailingAddr())
                .consignerRentalAmt(request.getConsignerRentalAmt())
                .itemDescription(request.getItemDescription())
                .consignerTelNo(request.getConsignerTelNo())
                .noaPpLat5Id(request.getNoaPpLat5Id())
                .build();
    }

    /**
     * Maps a path-bound ID variable alongside an update payload request into a Domain Model instance.
     * Keeps unassigned values as null wrapper positions so your patch layer can selectively bypass updates.
     */
    public NoaPpLat5Filing toDomain(Integer id, NoaPpLat5FilingUpdateRequest request) {
        if (request == null) return null;

        return NoaPpLat5Filing.builder()
                .id(id)
                .jurisdiction(request.getJurisdiction())
                .parcelId(request.getParcelId())
                .taxYear(request.getTaxYear()) // 💡 Allowed to carry null straight to patch validation
                .category(request.getCategory())
                .propertyType(request.getPropertyType())
                .filingYear(request.getFilingYear())
                .yearAcquired(request.getYearAcquired())
                .noOfUnits(request.getNoOfUnits())
                .acquisitionCost(request.getAcquisitionCost())
                .effectiveLife(request.getEffectiveLife())
                .consignerOwnerName(request.getConsignerOwnerName())
                .consignerMailingAddr(request.getConsignerMailingAddr())
                .consignerRentalAmt(request.getConsignerRentalAmt())
                .itemDescription(request.getItemDescription())
                .consignerTelNo(request.getConsignerTelNo())
                .noaPpLat5Id(request.getNoaPpLat5Id())
                .build();
    }

    /**
     * Translates a populated internal business Domain Model instance back out to a client-safe Response Record.
     */
    public NoaPpLat5FilingResponse toResponse(NoaPpLat5Filing domain) {
        if (domain == null) return null;

        return new NoaPpLat5FilingResponse(
                domain.getId(),
                domain.getJurisdiction(),
                domain.getParcelId(),
                domain.getTaxYear(),
                domain.getCategory(),
                domain.getPropertyType(),
                domain.getFilingYear(),
                domain.getYearAcquired(),
                domain.getNoOfUnits(),
                domain.getAcquisitionCost(),
                domain.getEffectiveLife(),
                domain.getConsignerOwnerName(),
                domain.getConsignerMailingAddr(),
                domain.getConsignerRentalAmt(),
                domain.getItemDescription(),
                domain.getConsignerTelNo(),
                domain.getNoaPpLat5Id()
        );
    }
}
