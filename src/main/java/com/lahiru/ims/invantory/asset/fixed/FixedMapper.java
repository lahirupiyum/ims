package com.lahiru.ims.invantory.asset.fixed;

import com.lahiru.ims.common.mapper.GenericMapper;
import com.lahiru.ims.invantory.asset.dto.AssetRequestDto;
import com.lahiru.ims.invantory.asset.dto.AssetResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FixedMapper extends GenericMapper<AssetRequestDto, AssetResponseDto, Fixed> {
}
