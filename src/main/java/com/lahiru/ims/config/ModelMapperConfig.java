package com.lahiru.ims.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    @Bean(name = "customModelMapper")
    public ModelMapper modelMapperConfig(){
        return new ModelMapper();
    }
}
