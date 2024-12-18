package com.lahiru.ims.feature.inventory.type;

import com.lahiru.ims.common.enums.AssetType;
import com.lahiru.ims.feature.inventory.manufacturer.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TypeRepo extends JpaRepository<Type,Integer> {
    List<Type> findAllByAssetType(AssetType assetType);
}
