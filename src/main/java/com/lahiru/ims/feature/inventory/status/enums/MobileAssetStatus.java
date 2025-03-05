package com.lahiru.ims.feature.inventory.status.enums;

import com.lahiru.ims.common.enums.DataSeederEnum;

public enum MobileAssetStatus implements DataSeederEnum {
    IN_USE("In Use"),
    AVAILABLE("Available"),
    UNDER_REPAIR("Under Repair")
    ;

    private final String displayName;

    MobileAssetStatus(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String getDisplayName() {
        return this.displayName;
    }
}
