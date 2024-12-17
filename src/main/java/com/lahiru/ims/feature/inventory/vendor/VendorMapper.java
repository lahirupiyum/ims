//package com.lahiru.ims.invantory.vendor;
//
//
//import com.lahiru.ims.invantory.vendor.dto.VendorRequestDto;
//import com.lahiru.ims.invantory.vendor.dto.VendorResponseDto;
//
//public class VendorMapper {
//    public static VendorResponseDto toDto(Vendor vendor) {
//        return new VendorResponseDto(
//            vendor.getId(),
//            vendor.getName(),
//            vendor.getEmail(),
//            vendor.getContactNo()
//        );
//    }
//
//    public static Vendor toModel(VendorRequestDto requestDto) {
//        return new Vendor(
//            requestDto.getName(),
//            requestDto.getEmail(),
//            requestDto.getContactNo()
//        );
//    }
//}
