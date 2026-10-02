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
}
