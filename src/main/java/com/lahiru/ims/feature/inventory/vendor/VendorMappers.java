//package com.lahiru.ims.feature.inventory.vendor;
//
//
//import com.lahiru.ims.common.mapper.GenericMapper;
//import com.lahiru.ims.feature.inventory.vendor.dto.VendorRequestDto;
//import com.lahiru.ims.feature.inventory.vendor.dto.VendorResponseDto;
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//
//@Mapper(componentModel = "spring")
//public interface VendorMappers extends GenericMapper<VendorRequestDto, VendorResponseDto,Vendor> {
//    @Override
//    @Mapping(target = "id", ignore = true)
//    @Mapping(target = "isActive", ignore = true)
//    Vendor toEntity(VendorRequestDto requestDto);
//}
