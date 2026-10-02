package com.opao.pp_api.features.property_asset.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PropertyAsset {
    private Integer id;
    private int sectionNumber;
    private String category;
    private String propertyType; 
    private String assetDescription;
    private int effectiveLife;

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
        if (!(object instanceof PropertyAsset)) {
            return false;
        }
        PropertyAsset other = (PropertyAsset) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "com.opao.pp_api.features.property_asset.model.PropertyAsset[ id=" + id + " ]";
    }
}
