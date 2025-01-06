package com.lahiru.ims.feature.inventory.type.enums;

import com.lahiru.ims.common.enums.DataSeederEnum;

public enum NetworkAsset implements DataSeederEnum {
    ROUTER("Router"),
    PROVIDER_EDGE_ROUTER("PE Router"),
    SWITCH("Switch");

    private final String displayName;

    NetworkAsset(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String getDisplayName() {
        return this.displayName;
    }
}
