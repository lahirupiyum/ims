package com.lahiru.ims.feature.inventory.status.enums;

import com.lahiru.ims.common.enums.DataSeederEnum;

public enum NetworkAssetStatus implements DataSeederEnum {
    IN_USE("In Use"),
    SOLD("Sold"),
    RENTED("Rented"),
    LOANED("Loaned"),
    AVAILABLE("Available"),
    ;

    private final String displayName;

    NetworkAssetStatus(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String getDisplayName() {
        return this.displayName;
    }
}
