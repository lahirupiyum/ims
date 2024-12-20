package com.lahiru.ims;

import com.lahiru.ims.feature.inventory.location.Location;
import com.lahiru.ims.feature.inventory.location.LocationRepo;
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
    public CommandLineRunner loadData(LocationRepo locationRepo) {
        return args -> {
            List<Location> locations = new ArrayList<>(List.of(new Location("Taj-Hotel", "colombo-02"), new Location("Orin-Hotel", "colombo-07")));
            // Add roles
            if (locationRepo.count() == 0) {
                locationRepo.saveAll(locations);
            }

        };
    }

}
