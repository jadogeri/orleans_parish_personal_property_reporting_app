package com.opao.pp_api.features.user_status.constants;

import com.opao.pp_api.features.user_status.model.UserStatus;
import java.util.List;

public final class UserStatuses {

    private UserStatuses() {}

    public static final UserStatus DISABLED = new UserStatus(1, "Disabled");
    public static final UserStatus ENABLED = new UserStatus(2, "Enabled");
    public static final UserStatus LOCKED = new UserStatus(3, "Locked");

    public static final List<UserStatus> ALL_STATUSES = List.of(DISABLED, ENABLED, LOCKED);

    /**
     * Finds a UserStatus by its numeric ID.
     * 
     * @param id The ID to look up
     * @return The matching UserStatus
     * @throws IllegalArgumentException if no status matches the given ID
     */
    public static UserStatus fromId(int id) {
        return ALL_STATUSES.stream()
                .filter(status -> status.getId() == id) // Assumes UserStatus has a getId() method
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid status ID: " + id));
    }
}
