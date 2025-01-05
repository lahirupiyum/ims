package com.lahiru.ims.feature.customer.service.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum ManageStatus {
    MANAGEABLE,
    UNMANAGEABLE;

    @JsonCreator
    public static ManageStatus fromString(String value) {
        return ManageStatus.valueOf(value.toUpperCase());
    }
}
