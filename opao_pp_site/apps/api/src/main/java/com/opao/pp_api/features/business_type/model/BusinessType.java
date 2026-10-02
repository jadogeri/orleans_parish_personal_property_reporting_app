
package com.opao.pp_api.features.business_type.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BusinessType {
    private Integer businessTypeId;
    private Integer code;
    private String description;      

        @Override
    public int hashCode() {
        int hash = 0;
        hash += (businessTypeId != null ? businessTypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BusinessType)) {
            return false;
        }
        BusinessType other = (BusinessType) object;
        if ((this.businessTypeId == null && other.businessTypeId != null)
                || (this.businessTypeId != null && !this.businessTypeId.equals(other.businessTypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.opao.pp_api.features.business_type.model.BusinessType[ businessTypeId=" + businessTypeId + " ]";
    }
}
