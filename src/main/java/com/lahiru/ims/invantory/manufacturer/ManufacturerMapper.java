package com.lahiru.ims.invantory.manufacturer;

import com.lahiru.ims.common.mapper.GenericMapper;
import com.lahiru.ims.invantory.manufacturer.dto.ManufacturerRequestDto;
import com.lahiru.ims.invantory.manufacturer.dto.ManufacturerResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ManufacturerMapper extends GenericMapper<ManufacturerRequestDto, ManufacturerResponseDto, Manufacturer> {
}
