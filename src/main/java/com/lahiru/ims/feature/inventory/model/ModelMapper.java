package com.lahiru.ims.feature.inventory.model;


import com.lahiru.ims.common.mapper.GenericMapper;
import com.lahiru.ims.feature.inventory.model.dto.ModelRequestDto;
import com.lahiru.ims.feature.inventory.model.dto.ModelResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ModelMapper extends GenericMapper<ModelRequestDto, ModelResponseDto, Model> {
}
