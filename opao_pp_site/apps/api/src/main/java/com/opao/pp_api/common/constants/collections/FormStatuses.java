package com.opao.pp_api.common.constants.collections;

import com.opao.pp_api.features.form_status.model.FormStatus;

public final class FormStatuses {

    private FormStatuses() {}

    static public final FormStatus NEW = new FormStatus(1);
    static public final FormStatus IN_PROGRESS = new FormStatus(2);
    static public final FormStatus SUBMITTED = new FormStatus(3);
    static public final FormStatus CLOSED = new FormStatus(4);
    
}
