package com.opao.pp_api.features.noa_pp_lat5.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

import com.opao.pp_api.common.validation.ValidJurisdiction;
import com.opao.pp_api.common.validation.ValidParcelAddress;
import com.opao.pp_api.common.validation.ValidEmail;
import com.opao.pp_api.common.validation.ValidPhoneNumber;
import com.opao.pp_api.features.noa_pp_lat5.constants.NoaPpLat5Constants;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NoaPpLat5CreateRequest {

    @ValidJurisdiction
    private String jurisdiction;       

    @ValidParcelAddress 
    private String parcelId;           

    @Size(max = NoaPpLat5Constants.ALTID_MAX_LENGTH, message = "Alternate ID cannot exceed {max} characters")
    private String alternateId;        

    @NotNull(message = "Tax year is required")
    private Integer taxYear;               

    @NotBlank(message = "Owner name cannot be blank")
    @Size(max = NoaPpLat5Constants.OWNERNAME_MAX_LENGTH, message = "Owner name cannot exceed {max} characters")
    private String ownerName;          

    @NotBlank(message = "Address line 1 cannot be blank")
    @Size(max = NoaPpLat5Constants.ADDR1_MAX_LENGTH, message = "Address line 1 cannot exceed {max} characters")
    private String address1;           

    @Size(max = NoaPpLat5Constants.ADDR2_MAX_LENGTH, message = "Address line 2 cannot exceed {max} characters")
    private String address2;           

    @NotBlank(message = "City name cannot be blank")
    @Size(max = NoaPpLat5Constants.CITYNAME_MAX_LENGTH, message = "City name cannot exceed {max} characters")
    private String cityName;           

    @NotBlank(message = "State code cannot be blank")
    @Size(max = NoaPpLat5Constants.STATECODE_MAX_LENGTH, message = "State code must be exactly {max} characters")
    private String stateCode;          

    @NotBlank(message = "Zip code cannot be blank")
    @Size(max = NoaPpLat5Constants.ZIP1_MAX_LENGTH, message = "Zip code cannot exceed {max} characters")
    private String zipCode;            

    @NotBlank(message = "PIN cannot be blank")
    @Size(max = NoaPpLat5Constants.PIN_MAX_LENGTH, message = "PIN cannot exceed {max} characters")
    private String pin;
    
    @Size(max = NoaPpLat5Constants.CONTACT_NAME_MAX_LENGTH, message = "Contact name cannot exceed {max} characters")
    private String contactName;

    @ValidPhoneNumber.Optional
    private String contactPhone;

    @Size(max = NoaPpLat5Constants.CONTACT_FAX_MAX_LENGTH, message = "Contact fax cannot exceed {max} characters")
    private String contactFax;

    @ValidEmail.Optional
    private String contactEmail;

    private Boolean contactSendEmails;
    
    @Size(max = NoaPpLat5Constants.PROPERTY_ADDRESS_MAX_LENGTH, message = "Property address cannot exceed {max} characters")
    private String propertyAddress;

    @Size(max = NoaPpLat5Constants.TAXPAYER_NAME_MAX_LENGTH, message = "Taxpayer name cannot exceed {max} characters")
    private String taxpayerName;

    private LocalDate taxpayerPreparedDate;
    
    @Size(max = NoaPpLat5Constants.TAX_PREPARER_NAME_MAX_LENGTH, message = "Tax preparer name cannot exceed {max} characters")
    private String taxPreparerName;

    @ValidPhoneNumber.Optional
    private String taxPreparerPhone;

    @ValidEmail.Optional
    private String taxPreparerEmail;

    private LocalDate taxPreparerPreparedDate;
    
    @NotNull(message = "Form ID mapping is required")
    private Integer formId;

    @NotNull(message = "Business Type ID mapping is required")
    private Integer businessTypeId; 
}
