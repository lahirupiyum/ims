package com.lahiru.ims.feature.inventory.manufacturer;

import com.lahiru.ims.common.enums.AssetType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ManufacturerRepo extends JpaRepository<Manufacturer, Integer> {
    List<Manufacturer> findAllByAssetType(AssetType assetType);
}
