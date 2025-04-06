package com.lahiru.ims.feature.customer.service.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum ProvisioningStatus {
    PROVISIONED("PROVISIONED"),
    UNDER_PROVISION("UNDER PROVISION");

    ProvisioningStatus(String s) {
    }

    @JsonCreator
    public static ProvisioningStatus fromString(String value) {
        return ProvisioningStatus.valueOf(value.toUpperCase());
    }
}
