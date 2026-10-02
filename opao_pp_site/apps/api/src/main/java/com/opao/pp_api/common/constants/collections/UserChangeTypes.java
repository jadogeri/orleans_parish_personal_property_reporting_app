package com.opao.pp_api.common.constants.collections;

import com.opao.pp_api.features.user_change_type.model.UserChangeType;

public final class UserChangeTypes {
    
    private UserChangeTypes() {}

    static public final UserChangeType ACTIVATE = new UserChangeType(1);
    static public final UserChangeType CHANGE_PASSWORD = new UserChangeType(2);
    static public final UserChangeType GET_USERNAME = new UserChangeType(3);
}
