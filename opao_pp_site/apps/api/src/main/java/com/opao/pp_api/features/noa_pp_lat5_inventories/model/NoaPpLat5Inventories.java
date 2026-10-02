package com.opao.pp_api.features.noa_pp_lat5_inventories.model;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NoaPpLat5Inventories {
    private Integer id;
    private String jurisdiction;    // Cleaned up 'jur'
    private String parcelId;        // Cleaned up 'parid'
    private int taxYear;            // Cleaned up 'taxyr'
    private int filingYear;         // Cleaned up 'fileyr'
    private String inventoryType;
    private Integer inventoryMonth;
    private Long inventoryAmount;   // Cleaned up 'inventoryAmt'
    
    // Core Domain Relational Linkage
    private Integer noaPpLat5Id;    // Parent link decoupled as an ID primitive

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
        if (!(object instanceof NoaPpLat5Inventories)) {
            return false;
        }
        NoaPpLat5Inventories other = (NoaPpLat5Inventories) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.opao.pp_api.features.noa_pp_lat5_inventories.model.NoaPpLat5Inventories[ id=" + id + " ]";
    }
}
