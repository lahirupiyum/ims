package com.lahiru.ims.feature.inventory.asset.fixed;


import com.lahiru.ims.common.mapper.GenericMapper;
import com.lahiru.ims.feature.inventory.asset.dto.AssetRequestDto;
import com.lahiru.ims.feature.inventory.asset.dto.AssetResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FixedMapper extends GenericMapper<AssetRequestDto, AssetResponseDto, Fixed> {
}
