package com.lahiru.ims;

import com.lahiru.ims.common.enums.AssetType;
import com.lahiru.ims.feature.inventory.location.Location;
import com.lahiru.ims.feature.inventory.location.LocationRepo;
import com.lahiru.ims.feature.inventory.status.Status;
import com.lahiru.ims.feature.inventory.status.StatusRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;


@SpringBootApplication
public class ImsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ImsApplication.class, args);
    }

    @Bean
    public CommandLineRunner loadData(LocationRepo locationRepo, StatusRepo statusRepo) {
        return args -> {
            List<Location> locations = new ArrayList<>(List.of(new Location("Taj-Hotel", "colombo-02"), new Location("Orin-Hotel", "colombo-07")));
            List<Status>status =new ArrayList<>(List
                    .of(
                            new Status("USE", AssetType.FIXED),
                            new Status("REMOVE", AssetType.FIXED),
                            new Status("SOLD", AssetType.FIXED),
                            new Status("USE", AssetType.NETWORK),
                            new Status("SOLD", AssetType.NETWORK),
                            new Status("REMOVE", AssetType.NETWORK),
                            new Status("USE", AssetType.MOBILE),
                            new Status("SOLD", AssetType.MOBILE),
                            new Status("REMOVE", AssetType.MOBILE)
                    ));
            // Add roles
            if (locationRepo.count() == 0) {
                locationRepo.saveAll(locations);
            }
            if(statusRepo.count()==0){
                statusRepo.saveAll(status);
            }

        };
    }

}
