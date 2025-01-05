package com.lahiru.ims.feature.customer.customer;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum CustomerPriority {
    PLATINUM,
    GOLD,
    SILVER,
    BRONZE;

    @JsonCreator
    public static CustomerPriority fromString(String value) {
        return CustomerPriority.valueOf(value.toUpperCase());
    }
}
