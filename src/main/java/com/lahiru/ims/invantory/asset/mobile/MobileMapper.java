package com.lahiru.ims.invantory.asset.mobile;

import com.lahiru.ims.common.mapper.GenericMapper;
import com.lahiru.ims.invantory.asset.dto.AssetRequestDto;
import com.lahiru.ims.invantory.asset.dto.AssetResponseDto;
import com.lahiru.ims.invantory.asset.fixed.Fixed;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MobileMapper extends GenericMapper<AssetRequestDto, AssetResponseDto, Fixed> {
}
