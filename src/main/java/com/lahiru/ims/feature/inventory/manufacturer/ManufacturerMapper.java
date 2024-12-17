package com.lahiru.ims.feature.inventory.manufacturer;


import com.lahiru.ims.common.mapper.GenericMapper;
import com.lahiru.ims.feature.inventory.manufacturer.dto.ManufacturerRequestDto;
import com.lahiru.ims.feature.inventory.manufacturer.dto.ManufacturerResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ManufacturerMapper extends GenericMapper<ManufacturerRequestDto, ManufacturerResponseDto, Manufacturer> {
}
