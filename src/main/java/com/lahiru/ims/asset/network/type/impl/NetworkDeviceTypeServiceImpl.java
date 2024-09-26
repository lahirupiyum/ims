package com.lahiru.ims.asset.network.type.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lahiru.ims.asset.network.type.NetworkDeviceType;
import com.lahiru.ims.asset.network.type.NetworkDeviceTypeMapper;
import com.lahiru.ims.asset.network.type.NetworkDeviceTypeRepo;
import com.lahiru.ims.asset.network.type.NetworkDeviceTypeService;
import com.lahiru.ims.asset.network.type.dto.NetworkDeviceTypeRequestDto;
import com.lahiru.ims.asset.network.type.dto.NetworkDeviceTypeResponseDto;
import com.lahiru.ims.common.GenericDao;
import com.lahiru.ims.common.dto.PaginationResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class NetworkDeviceTypeServiceImpl implements NetworkDeviceTypeService {

    private final NetworkDeviceTypeRepo networkDeviceTypeRepo;
    private final GenericDao genericDao;

    @Override
    public PaginationResponse<NetworkDeviceTypeResponseDto> findByPageWise(int page, int pageSize) throws Exception {
        throw new UnsupportedOperationException("Unimplemented method 'findByPageWise'");
    }

    @Override
    public List<NetworkDeviceTypeResponseDto> findAll() throws Exception {
        List<NetworkDeviceType> networkDeviceTypeList = networkDeviceTypeRepo.findAll();
        return networkDeviceTypeList.stream().map(NetworkDeviceTypeMapper::toDto).toList();
    }

    @Override
    public NetworkDeviceTypeResponseDto createOne(NetworkDeviceTypeRequestDto requestDto) throws Exception {
       NetworkDeviceType networkDeviceType = NetworkDeviceTypeMapper.toModel(requestDto);
       NetworkDeviceType savedNetworkDeviceType = networkDeviceTypeRepo.save(networkDeviceType);
        return NetworkDeviceTypeMapper.toDto(savedNetworkDeviceType);
    }

    @Override
    public NetworkDeviceTypeResponseDto updateOne(int id, NetworkDeviceTypeRequestDto requestDto) throws Exception {
        NetworkDeviceType type = genericDao.getOne(id, networkDeviceTypeRepo, "Network Device");
        type.setName(requestDto.getName());
        NetworkDeviceType updaNetworkDeviceType = networkDeviceTypeRepo.save(type);
        return NetworkDeviceTypeMapper.toDto(updaNetworkDeviceType);
    }

    @Override
    public NetworkDeviceTypeResponseDto deleteOne(int id) throws Exception {
        NetworkDeviceType type = genericDao.getOne(id, networkDeviceTypeRepo, "Network Device");
        networkDeviceTypeRepo.delete(type);
        return NetworkDeviceTypeMapper.toDto(type);
    }        
}
