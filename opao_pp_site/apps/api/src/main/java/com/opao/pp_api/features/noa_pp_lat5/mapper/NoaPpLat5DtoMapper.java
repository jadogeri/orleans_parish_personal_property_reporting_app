package com.opao.pp_api.features.noa_pp_lat5.mapper;

import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.opao.pp_api.features.business_type.model.BusinessType;
import com.opao.pp_api.features.business_type.dto.response.BusinessTypeResponse;
import com.opao.pp_api.features.noa_pp_lat5.dto.request.NoaPpLat5CreateRequest;
import com.opao.pp_api.features.noa_pp_lat5.dto.request.NoaPpLat5UpdateRequest;
import com.opao.pp_api.features.noa_pp_lat5.dto.response.NoaPpLat5Response;
import com.opao.pp_api.features.noa_pp_lat5.model.NoaPpLat5;
import com.opao.pp_api.features.noa_pp_lat5_filing.dto.response.NoaPpLat5FilingResponse;
import com.opao.pp_api.features.noa_pp_lat5_inventories.dto.response.NoaPpLat5InventoriesResponse;

@Component
public class NoaPpLat5DtoMapper {

    /**
     * Maps an incoming creation request payload to a fresh domain aggregate state instance.
     * Flat identity values map out to shallow target entities to fulfill downstream link rules.
     */
    public NoaPpLat5 toDomain(NoaPpLat5CreateRequest request) {
        if (request == null) return null;

        return NoaPpLat5.builder()
                .jurisdiction(request.getJurisdiction())
                .parcelId(request.getParcelId())
                .alternateId(request.getAlternateId())
                .taxYear(request.getTaxYear() != null ? request.getTaxYear() : 0)
                .ownerName(request.getOwnerName())
                .address1(request.getAddress1())
                .address2(request.getAddress2())
                .cityName(request.getCityName())
                .stateCode(request.getStateCode())
                .zipCode(request.getZipCode())
                .pin(request.getPin())
                .contactName(request.getContactName())
                .contactPhone(request.getContactPhone())
                .contactFax(request.getContactFax())
                .contactEmail(request.getContactEmail())
                .contactSendEmails(request.getContactSendEmails() != null ? request.getContactSendEmails() : false)
                .propertyAddress(request.getPropertyAddress())
                .taxpayerName(request.getTaxpayerName())
                .taxpayerPreparedDate(request.getTaxpayerPreparedDate())
                .taxPreparerName(request.getTaxPreparerName())
                .taxPreparerPhone(request.getTaxPreparerPhone())
                .taxPreparerEmail(request.getTaxPreparerEmail())
                .taxPreparerPreparedDate(request.getTaxPreparerPreparedDate())
                .formId(request.getFormId())
                // 💡 Hydrates a shallow nested reference entity safely to pass through to the business layer
                .businessType(request.getBusinessTypeId() != null ? 
                    BusinessType.builder().id(request.getBusinessTypeId()).build() : null)
                .filings(new ArrayList<>())
                .inventories(new ArrayList<>())
                .build();
    }

    /**
     * Maps an explicit URL PathVariable resource parameter alongside an update request payload block.
     * Primitives match true nullable object conversions so fields can be safely updated selectively.
     */
    public NoaPpLat5 toDomain(Integer id, NoaPpLat5UpdateRequest request) {
        if (request == null) return null;

        return NoaPpLat5.builder()
                .id(id)
                .jurisdiction(request.getJurisdiction())
                .parcelId(request.getParcelId())
                .alternateId(request.getAlternateId())
                .taxYear(request.getTaxYear()) // 💡 Carries standard Integer wrapper bounds cleanly for patch checks
                .ownerName(request.getOwnerName())
                .address1(request.getAddress1())
                .address2(request.getAddress2())
                .cityName(request.getCityName())
                .stateCode(request.getStateCode())
                .zipCode(request.getZipCode())
                .pin(request.getPin())
                .contactName(request.getContactName())
                .contactPhone(request.getContactPhone())
                .contactFax(request.getContactFax())
                .contactEmail(request.getContactEmail())
                .contactSendEmails(request.getContactSendEmails())
                .propertyAddress(request.getPropertyAddress())
                .taxpayerName(request.getTaxpayerName())
                .taxpayerPreparedDate(request.getTaxpayerPreparedDate())
                .taxPreparerName(request.getTaxPreparerName())
                .taxPreparerPhone(request.getTaxPreparerPhone())
                .taxPreparerEmail(request.getTaxPreparerEmail())
                .taxPreparerPreparedDate(request.getTaxPreparerPreparedDate())
                .formId(request.getFormId())
                .businessType(request.getBusinessTypeId() != null ? 
                    BusinessType.builder().id(request.getBusinessTypeId()).build() : null)
                .build();
    }

    /**
     * Converts an un-marshaled core business domain entity model into a client-safe, deep hierarchical JSON response structure.
     */
    public NoaPpLat5Response toResponse(NoaPpLat5 domain) {
        if (domain == null) return null;

        // 💡 Gracefully maps out the nested object tree using safe DTO variants
        BusinessTypeResponse businessTypeDto = null;
        if (domain.getBusinessType() != null) {
            businessTypeDto = new BusinessTypeResponse(
                domain.getBusinessType().getId(),
                domain.getBusinessType().getCode(),
                domain.getBusinessType().getDescription()
            );
        }

        List<NoaPpLat5FilingResponse> filingDtos = null;
        if (domain.getFilings() != null) {
            filingDtos = domain.getFilings().stream()
                .map(f -> new NoaPpLat5FilingResponse(
                    f.getId(), f.getJurisdiction(), f.getParcelId(), f.getTaxYear(),
                    f.getCategory(), f.getPropertyType(), f.getFilingYear(), f.getYearAcquired(),
                    f.getNoOfUnits(), f.getAcquisitionCost(), f.getEffectiveLife(),
                    f.getConsignerOwnerName(), f.getConsignerMailingAddr(), f.getConsignerRentalAmt(),
                    f.getItemDescription(), f.getConsignerTelNo(), f.getNoaPpLat5Id()
                ))
                .collect(Collectors.toList());
        }

        List<NoaPpLat5InventoriesResponse> inventoryDtos = null;
        if (domain.getInventories() != null) {
            inventoryDtos = domain.getInventories().stream()
                .map(i -> new NoaPpLat5InventoriesResponse(
                    i.getId(), i.getJurisdiction(), i.getParcelId(), i.getTaxYear(),
                    i.getFilingYear(), i.getInventoryType(), i.getInventoryMonth(), i.getInventoryAmount()
                ))
                .collect(Collectors.toList());
        }

        return new NoaPpLat5Response(
                domain.getId(),
                domain.getJurisdiction(),
                domain.getParcelId(),
                domain.getAlternateId(),
                domain.getTaxYear(),
                domain.getOwnerName(),
                domain.getAddress1(),
                domain.getAddress2(),
                domain.getCityName(),
                domain.getStateCode(),
                domain.getZipCode(),
                domain.getPin(),
                domain.getContactName(),
                domain.getContactPhone(),
                domain.getContactFax(),
                domain.getContactEmail(),
                domain.getContactSendEmails(),
                domain.getPropertyAddress(),
                domain.getTaxpayerName(),
                domain.getTaxpayerPreparedDate(),
                domain.getTaxPreparerName(),
                domain.getTaxPreparerPhone(),
                domain.getTaxPreparerEmail(),
                domain.getTaxPreparerPreparedDate(),
                domain.getFormId(),
                businessTypeDto,
                filingDtos,
                inventoryDtos
        );
    }
}
