//package com.lahiru.ims.invantory.type;
//
//import com.lahiru.ims.invantory.type.dto.NetworkDeviceTypeRequestDto;
//import com.lahiru.ims.invantory.type.dto.NetworkDeviceTypeResponseDto;
//
//public class NetworkDeviceTypeMapper {
//    public static NetworkDeviceType toModel(NetworkDeviceTypeRequestDto requestDto) {
//        NetworkDeviceType networkDeviceType = new NetworkDeviceType();
//        networkDeviceType.setName(requestDto.getName().toLowerCase());
//        networkDeviceType.setIsActive(true);
//        return networkDeviceType;
//    }
//
//    public static NetworkDeviceTypeResponseDto toDto(NetworkDeviceType networkDeviceType) {
//        return new NetworkDeviceTypeResponseDto(networkDeviceType.getId(), networkDeviceType.getName());
//    }
//}
