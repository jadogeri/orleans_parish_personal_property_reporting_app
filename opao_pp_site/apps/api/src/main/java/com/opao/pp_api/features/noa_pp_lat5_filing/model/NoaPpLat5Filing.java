package com.opao.pp_api.features.noa_pp_lat5_filing.model;


import lombok.Setter;
import com.opao.pp_api.features.property_asset.model.PropertyAsset;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NoaPpLat5Filing {
    private Integer id;
    private String jurisdiction;       
    private String parcelId;           
    private int taxYear;               
    private String category;
    private String propertyType;       
    private int filingYear;            
    private Integer yearAcquired;      
    private Integer noOfUnits;         
    private Long acquisitionCost;
    private Integer effectiveLife;
    
    // Consigner / Owner Details
    private String consignerOwnerName;
    private String consignerMailingAddr;
    private Long consignerRentalAmt;
    private String itemDescription;
    private String consignerTelNo;
    
    // Core Domain Relational Linkages
    private Integer noaPpLat5Id;        
    private PropertyAsset propertyAsset; 

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        if (!(object instanceof NoaPpLat5Filing)) {
            return false;
        }
        NoaPpLat5Filing other = (NoaPpLat5Filing) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.opao.pp_api.features.noa_pp_lat5_filing.model.NoaPpLat5Filing[ id=" + id + " ]";
    }
    
}
