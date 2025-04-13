package com.lahiru.ims.feature.inventory.status.enums;

import com.lahiru.ims.common.enums.DataSeederEnum;

public enum FixedAssetStatus implements DataSeederEnum {
    IN_USE("In Use"),
    OUT_OF_SERVICE("Out of Service"),
    ;

    private final String displayName;

    FixedAssetStatus(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String getDisplayName() {
        return this.displayName;
    }
}
