package com.lahiru.ims.feature.inventory.asset.network;


import com.lahiru.ims.common.mapper.GenericMapper;
import com.lahiru.ims.feature.inventory.asset.dto.AssetRequestDto;
import com.lahiru.ims.feature.inventory.asset.dto.AssetResponseDto;
import com.lahiru.ims.feature.inventory.asset.fixed.Fixed;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NetworkMapper extends GenericMapper<AssetRequestDto, AssetResponseDto, Fixed> {
}
