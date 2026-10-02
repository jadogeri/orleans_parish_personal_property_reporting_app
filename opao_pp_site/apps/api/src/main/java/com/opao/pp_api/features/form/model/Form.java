package com.opao.pp_api.features.form.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Form {
    private Integer id;
    private String title;
    private int filingYear;
    private LocalDateTime lastModifiedDate; 
    private String billNumber;
    private String pin;    
    private Integer formTypeId;
    private String statusName;
    private Integer userId;    
    private boolean hasLineItems; 

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Form)) {
            return false;
        }
        Form other = (Form) object;
        if ((this.id == null && other.id != null)
                || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.opao.pp_api.features.form.model.Form[ id=" + id + " ]";
    }   
}
