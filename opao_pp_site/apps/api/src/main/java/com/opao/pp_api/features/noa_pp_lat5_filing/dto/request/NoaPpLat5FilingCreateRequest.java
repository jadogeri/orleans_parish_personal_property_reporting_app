package com.opao.pp_api.features.noa_pp_lat5_filing.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.opao.pp_api.features.noa_pp_lat5_filing.constants.NoaPpLat5FilingConstants;
import com.opao.pp_api.features.noa_pp_lat5_filing.validation.ValidAcquisitionYear;
import com.opao.pp_api.common.validation.ValidJurisdiction;
import com.opao.pp_api.common.validation.ValidParcelAddress;
import com.opao.pp_api.common.validation.ValidPhoneNumber;
import com.opao.pp_api.common.validation.ValidForeignId;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NoaPpLat5FilingCreateRequest {

    @ValidJurisdiction
    private String jurisdiction;       

    @ValidParcelAddress
    private String parcelId;           

    @NotNull(message = "Tax year is required")
    private Integer taxYear;               

    @NotBlank(message = "Category cannot be blank")
    @Size(min = NoaPpLat5FilingConstants.CATEGORY_MIN_LENGTH, max = NoaPpLat5FilingConstants.CATEGORY_MAX_LENGTH,
          message = "Category must be between {min} and {max} characters")
    private String category;

    @NotBlank(message = "Property type cannot be blank")
    @Size(min = NoaPpLat5FilingConstants.PPTYPE_MIN_LENGTH, max = NoaPpLat5FilingConstants.PPTYPE_MAX_LENGTH,
          message = "Property type must be between {min} and {max} characters")
    private String propertyType;       

    @NotNull(message = "Filing year is required")
    private Integer filingYear;            

    @ValidAcquisitionYear
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

    @ValidPhoneNumber
    private String consignerTelNo;
    
    @ValidForeignId
    @NotNull(message = "Linked NoaPpLat5 ID reference is required")
    private Integer noaPpLat5Id;        
}
