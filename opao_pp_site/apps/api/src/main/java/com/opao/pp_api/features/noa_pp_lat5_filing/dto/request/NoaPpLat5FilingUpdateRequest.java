package com.opao.pp_api.features.noa_pp_lat5_filing.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import com.opao.pp_api.common.validation.ValidParcelAddress;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.opao.pp_api.features.noa_pp_lat5_filing.dto.constants.NoaPpLat5FilingConstants;
import com.opao.pp_api.features.noa_pp_lat5_filing.validation.ValidAcquisitionYear;
import com.opao.pp_api.common.validation.ValidForeignId;
import com.opao.pp_api.common.validation.ValidJurisdiction;
import com.opao.pp_api.common.validation.ValidPhoneNumber;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NoaPpLat5FilingUpdateRequest {

    @NotNull(message = "Filing ID is required for updates")
    private Integer id;

    @ValidJurisdiction.Optional
    private String jurisdiction;

    @ValidParcelAddress.Optional
    private String parcelId;

    // Converted to primitive wrapper Integer to allow true optional null updates
    private Integer taxYear;

    @Size(min = NoaPpLat5FilingConstants.CATEGORY_MIN_LENGTH, max = NoaPpLat5FilingConstants.CATEGORY_MAX_LENGTH,
          message = "Category length must be between {min} and {max} characters")
    private String category;

    @Size(min = NoaPpLat5FilingConstants.PPTYPE_MIN_LENGTH, max = NoaPpLat5FilingConstants.PPTYPE_MAX_LENGTH,
          message = "Property type length must be between {min} and {max} characters")
    private String propertyType;

    // Converted to primitive wrapper Integer to allow true optional null updates
    private Integer filingYear;

    @ValidAcquisitionYear.Optional
    private Integer yearAcquired;

    private Integer noOfUnits;

    private Long acquisitionCost;

    private Integer effectiveLife;

    @Size(max = NoaPpLat5FilingConstants.COSIGNER_OWNER_NAME_MAX_LENGTH, 
          message = "Consigner owner name cannot exceed {max} characters")
    private String consignerOwnerName;

    @Size(max = NoaPpLat5FilingConstants.COSIGNER_MAILING_ADDR_MAX_LENGTH, 
          message = "Consigner mailing address cannot exceed {max} characters")
    private String consignerMailingAddr;

    private Long consignerRentalAmt;

    @Size(max = NoaPpLat5FilingConstants.ITEM_DESCRIPTION_MAX_LENGTH, 
          message = "Item description cannot exceed {max} characters")
    private String itemDescription;

    @ValidPhoneNumber.Optional
    private String consignerTelNo;

    @ValidForeignId.Optional
    private Integer noaPpLat5Id;
}
