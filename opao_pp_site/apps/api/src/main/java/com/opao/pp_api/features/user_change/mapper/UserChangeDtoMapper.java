package com.opao.pp_api.features.user_change.mapper;

import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

import com.opao.pp_api.features.user_change.dto.request.UserChangeCreateRequest;
import com.opao.pp_api.features.user_change.dto.response.UserChangeResponse;
import com.opao.pp_api.features.user_change.model.UserChange;

@Component
public class UserChangeDtoMapper {

    /**
     * Maps a creation request payload to a new UserChange domain object.
     * Automatically captures the structural timestamp when initialization starts.
     */
    public UserChange toDomain(UserChangeCreateRequest request) {
        if (request == null) return null;
        
        return UserChange.builder()
                .verificationCode(request.verificationCode())
                .userChangeTypeId(request.userChangeTypeId())
                .userId(request.userId())
                .initiatedTime(LocalDateTime.now()) // 💡 Enforces auto-timestamp generation upon creation entry
                .build();
    }

    /**
     * Converts an existing processing domain state instance to a client-safe response payload structure.
     */
    public UserChangeResponse toResponse(UserChange domain) {
        if (domain == null) return null;
        
        return new UserChangeResponse(
            domain.getId(),
            domain.getVerificationCode(),
            domain.getInitiatedTime(),
            domain.getUserChangeTypeId(),
            domain.getUserId()
        );
    }
}
