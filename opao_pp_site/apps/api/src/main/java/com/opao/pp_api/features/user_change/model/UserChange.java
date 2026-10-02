package com.opao.pp_api.features.user_change.model;

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
public class UserChange {
    private Integer id;
    private String verificationCode;
    private LocalDateTime initiatedTime;
    
    // Core Domain structural linkages (Flat IDs/Types instead of heavy JPA mapping trees)
    private Integer userChangeTypeId;
    private Integer userId;

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof UserChange)) {
            return false;
        }
        UserChange other = (UserChange) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.opao.pp_api.features.user_change.model.UserChange[ id=" + id + " ]";
    }
    
}
