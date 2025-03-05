package com.lahiru.ims.data;

import com.lahiru.ims.common.enums.AssetType;
import com.lahiru.ims.common.enums.DataSeederEnum;
import com.lahiru.ims.common.model.BasicInfoAudit;
import com.lahiru.ims.common.repository.BasicInfoRepo;
import com.lahiru.ims.feature.inventory.location.Location;
import com.lahiru.ims.feature.inventory.location.LocationRepo;
import com.lahiru.ims.feature.inventory.status.Status;
import com.lahiru.ims.feature.inventory.status.StatusRepo;
import com.lahiru.ims.feature.inventory.status.enums.FixedAssetStatus;
import com.lahiru.ims.feature.inventory.status.enums.MobileAssetStatus;
import com.lahiru.ims.feature.inventory.status.enums.NetworkAssetStatus;
import com.lahiru.ims.feature.inventory.type.Type;
import com.lahiru.ims.feature.inventory.type.TypeRepo;
import com.lahiru.ims.feature.inventory.type.enums.NetworkAsset;
import com.lahiru.ims.utils.EnumUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {
    private final TypeRepo typeRepo;
    private final StatusRepo statusRepo;
    private final LocationRepo locationRepo;

    @Override
    public void run(String... args) throws Exception {
        seedBasicStatus();
        seedBasicTypes();
        seedLocations();
    }

    private void seedLocations () {
        if (locationRepo.count() == 0) {
            List<Location> locations = new ArrayList<>(List.of(new Location("Taj-POP", "colombo-02"), new Location("Orin-POP", "colombo-07")));
            locationRepo.saveAll(locations);
        }
    }

    private void seedBasicStatus() {
        seedBasicInfo(statusRepo, AssetType.NETWORK, NetworkAssetStatus.class, Status.class);
        seedBasicInfo(statusRepo, AssetType.FIXED, FixedAssetStatus.class, Status.class);
        seedBasicInfo(statusRepo, AssetType.MOBILE, MobileAssetStatus.class, Status.class);
    }

    private void seedBasicTypes() {
        seedBasicInfo(typeRepo, AssetType.NETWORK, NetworkAsset.class, Type.class);
    }

    private <E extends Enum<E> & DataSeederEnum, Model extends BasicInfoAudit, Repo extends BasicInfoRepo<Model>> void seedBasicInfo(Repo repo, AssetType assetType, Class<E> enumClass, Class<Model> modelClass) {
        List<String> list = EnumUtils.extractEnum(enumClass);
        list.forEach(typeName -> {
            if (!repo.isExistsByNameAndAssetType(typeName, assetType)) {
                try {
                    Model instance = modelClass.getDeclaredConstructor().newInstance();
                    instance.setAssetType(assetType);
                    instance.setName(typeName);
                    repo.save(instance);
                } catch (InstantiationException | NoSuchMethodException | InvocationTargetException |
                         IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
