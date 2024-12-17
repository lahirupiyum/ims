//package com.lahiru.ims.invantory.type.impl;
//
//import com.lahiru.ims.common.dto.PaginationResponse;
//import com.lahiru.ims.exception.DataConflictException;
//import com.lahiru.ims.exception.NotFoundException;
//import com.lahiru.ims.invantory.type.NetworkDeviceType;
//import com.lahiru.ims.invantory.type.NetworkDeviceTypeMapper;
//import com.lahiru.ims.invantory.type.NetworkDeviceTypeRepo;
//import com.lahiru.ims.invantory.type.NetworkDeviceTypeService;
//import com.lahiru.ims.invantory.type.dto.NetworkDeviceTypeRequestDto;
//import com.lahiru.ims.invantory.type.dto.NetworkDeviceTypeResponseDto;
//import jakarta.transaction.Transactional;
//import lombok.RequiredArgsConstructor;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//@Transactional
//@RequiredArgsConstructor
//public class NetworkDeviceTypeServiceImpl implements NetworkDeviceTypeService {
//
//    public static final String NETWORK_DEVICE = "Network Device";
//    private final NetworkDeviceTypeRepo networkDeviceTypeRepo;
//
//    @Override
//    public PaginationResponse<NetworkDeviceTypeResponseDto> findByPageWise(int page, int pageSize) throws Exception {
//        Pageable pageable = PageRequest.of(page, pageSize);
//        Page<NetworkDeviceType> allActive = networkDeviceTypeRepo.findAllByIsActive(true, pageable);
//        List<NetworkDeviceTypeResponseDto> list = allActive.map(NetworkDeviceTypeMapper::toDto).stream().toList();
//        return new PaginationResponse<>(list, (int) allActive.getTotalElements());
//    }
//
//    @Override
//    public List<NetworkDeviceTypeResponseDto> findAll() throws Exception {
//        List<NetworkDeviceType> networkDeviceTypeList = networkDeviceTypeRepo.findAllByIsActive(true);
//        return networkDeviceTypeList.stream().map(NetworkDeviceTypeMapper::toDto).toList();
//    }
//
//    @Override
//    public NetworkDeviceTypeResponseDto createOne(NetworkDeviceTypeRequestDto requestDto) throws Exception {
//        if (networkDeviceTypeRepo.existsByNameAndIsActive(requestDto.getName(), true))
//            throw new DataConflictException("Network device type already exists");
//       NetworkDeviceType networkDeviceType = NetworkDeviceTypeMapper.toModel(requestDto);
//       NetworkDeviceType savedNetworkDeviceType = networkDeviceTypeRepo.save(networkDeviceType);
//        return NetworkDeviceTypeMapper.toDto(savedNetworkDeviceType);
//    }
//
//    @Override
//    public NetworkDeviceTypeResponseDto updateOne(int id, NetworkDeviceTypeRequestDto requestDto) throws Exception {
//        String updatedName = requestDto.getName();
//        NetworkDeviceType type = networkDeviceTypeRepo.findByIdAndIsActive(id, true)
//                        .orElseThrow(() -> new NotFoundException(NETWORK_DEVICE));
//        if (networkDeviceTypeRepo.existsByNameAndIsActive(updatedName, true) && type.getName().equals(updatedName))
//            throw new DataConflictException("Network device type already exists!");
//        type.setName(updatedName);
//        NetworkDeviceType updaNetworkDeviceType = networkDeviceTypeRepo.save(type);
//        return NetworkDeviceTypeMapper.toDto(updaNetworkDeviceType);
//    }
//
//    @Override
//    public NetworkDeviceTypeResponseDto deleteOne(int id) throws Exception {
//        NetworkDeviceType type = networkDeviceTypeRepo.findByIdAndIsActive(id, true)
//                        .orElseThrow(() -> new NotFoundException(NETWORK_DEVICE));
//        type.setIsActive(false);
//        NetworkDeviceType deletedType = networkDeviceTypeRepo.save(type);
//        return NetworkDeviceTypeMapper.toDto(deletedType);
//    }
//}
