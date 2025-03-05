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

}
