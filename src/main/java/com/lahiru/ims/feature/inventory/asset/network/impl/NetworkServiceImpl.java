package com.lahiru.ims.feature.inventory.asset.network.impl;


import com.lahiru.ims.common.GenericDao;
import com.lahiru.ims.common.dto.PaginationResponse;
import com.lahiru.ims.common.enums.AssetType;
import com.lahiru.ims.exception.NotFoundException;
import com.lahiru.ims.feature.inventory.asset.network.Network;
import com.lahiru.ims.feature.inventory.asset.network.NetworkRepo;
import com.lahiru.ims.feature.inventory.asset.network.NetworkService;
import com.lahiru.ims.feature.inventory.asset.network.dto.NetworkAssetRequestDto;
import com.lahiru.ims.feature.inventory.asset.network.dto.NetworkAssetResponseDto;
import com.lahiru.ims.feature.inventory.location.Location;
import com.lahiru.ims.feature.inventory.location.LocationService;
import com.lahiru.ims.feature.inventory.manufacturer.Manufacturer;
import com.lahiru.ims.feature.inventory.manufacturer.ManufacturerService;
import com.lahiru.ims.feature.inventory.model.Model;
import com.lahiru.ims.feature.inventory.model.ModelService;
import com.lahiru.ims.feature.inventory.model.dto.ModelDto;
import com.lahiru.ims.feature.inventory.status.Status;
import com.lahiru.ims.feature.inventory.status.StatusService;
import com.lahiru.ims.feature.inventory.status.dto.StatusDto;
import com.lahiru.ims.feature.inventory.type.Type;
import com.lahiru.ims.feature.inventory.type.TypeService;
import com.lahiru.ims.feature.inventory.type.dto.TypeDto;
import com.lahiru.ims.feature.inventory.vendor.Vendor;
import com.lahiru.ims.feature.inventory.vendor.VendorService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NetworkServiceImpl implements NetworkService {
    private final String NETWORK = "Network";
    private final ModelMapper modelMapper;
    private final ManufacturerService manufacturerService;
    private final TypeService typeService;
    private final ModelService modelService;
    private final StatusService statusService;
    private final VendorService vendorService;
    private final LocationService locationService;
    private final GenericDao genericDao;
    private final NetworkRepo networkRepo;
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Override
    public PaginationResponse<NetworkAssetResponseDto> findByPageWise(int page, int pageSize) throws Exception {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Network> networkPage = networkRepo.findAll(pageable);
        try {
            List<NetworkAssetResponseDto> responseDtoList = modelMapper
                    .map(networkPage.stream().toList(),
                            new TypeToken<List<NetworkAssetResponseDto>>() {
                            }.getType());
            return new PaginationResponse<>(
                    responseDtoList,
                    (int) networkPage.getTotalElements()
            );
        } catch (Exception e) {
            log.info("Model Mapper Error Generate From findByPageWise");
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<NetworkAssetResponseDto> findAll() throws Exception {
        List<Network> networkList = networkRepo.findAll();
        try {
            List<NetworkAssetResponseDto> responseNetworks = modelMapper.map(networkList, new TypeToken<List<NetworkAssetResponseDto>>() {
            }.getType());
            return (!responseNetworks.isEmpty()) ? responseNetworks : Collections.emptyList();
        } catch (Exception e) {
            log.info("Model Mapper Error Generate By Find All {}", NETWORK);
            throw new Exception(e);
        }
    }

    @Override
    public NetworkAssetResponseDto createOne(NetworkAssetRequestDto networkAssetRequestDto) throws Exception {
        Network toSave = convertDtoToEntity(networkAssetRequestDto);
        Network savedNetwork = networkRepo.save(toSave);
        log.info("Saved NETWORK Asset !");
        return convertEntityToDto(savedNetwork, "createOne()");

    }

    @Override
    public NetworkAssetResponseDto updateOne(int id, NetworkAssetRequestDto networkAssetRequestDto) throws Exception {
        Network foundNetwork = networkRepo.findById(id).orElseThrow(() -> new NotFoundException(NETWORK));
        Network updatedNetwork = convertDtoToEntity(networkAssetRequestDto);
        updatedNetwork.setId(foundNetwork.getId());
        Network saveUpdated = networkRepo.saveAndFlush(updatedNetwork);
        return convertEntityToDto(saveUpdated, "updateOne()");

    }

    @Override
    public NetworkAssetResponseDto deleteOne(int id) throws Exception {
        Network network = networkRepo.findById(id).orElseThrow(() -> new NotFoundException(NETWORK));
        networkRepo.deleteById(network.getId());
        log.info("Deleted Successful id:{}", network.getId());
        return convertEntityToDto(network, "deleteOne()");
    }

    public Network convertDtoToEntity(NetworkAssetRequestDto networkAssetRequestDto) throws Exception {
        Manufacturer manufacturer = genericDao.checkAndCreate(AssetType.NETWORK, networkAssetRequestDto.getManufacturer(), manufacturerService);
        Type type = genericDao.checkAndCreate(AssetType.NETWORK, networkAssetRequestDto.getType(), typeService);
        Model model = genericDao.checkAndCreate(AssetType.NETWORK, networkAssetRequestDto.getModel(), modelService);
        Status status = statusService.findOne(networkAssetRequestDto.getStatusId());
        Location location = locationService.findOne(networkAssetRequestDto.getLocationId());
        Vendor vendor = vendorService.findOne(networkAssetRequestDto.getVendorId());
        try {
            ModelMapper modelMapper1 = new ModelMapper();
            modelMapper1.addMappings(new PropertyMap<NetworkAssetRequestDto, Network>() {
                @Override
                protected void configure() {
                    skip(destination.getId());
                }
            });
            Network network = modelMapper1.map(networkAssetRequestDto, Network.class);
            network.setManufacturer(manufacturer);
            network.setType(type);
            network.setModel(model);
            network.setStatus(status);
            network.setLocation(location);
            network.setVendor(vendor);
            return network;
        } catch (Exception e) {
            log.error("Model Mapper Converting Error Where {} -> {} ", NETWORK, "ConvertDtoToEntity");
            throw new RuntimeException(e.getMessage());
        }
    }

    public NetworkAssetResponseDto convertEntityToDto(Network network, String methodName) {
        try {
            return modelMapper.map(network, NetworkAssetResponseDto.class);
        } catch (Exception e) {
            log.info("Model Mapper Error Generate By Convert Entity To Response From : {}", methodName);
            throw new RuntimeException(e);
        }
    }

    // Get Model , Type ,Status
    @Override
    public List<ModelDto> getAllModel() throws Exception {
        List<Model> modelList = modelService.getAll(AssetType.FIXED);
        List<ModelDto> modelDtoList = modelMapper.map(modelList, new TypeToken<List<ModelDto>>() {
        }.getType());
        return (!modelDtoList.isEmpty()) ? modelDtoList : Collections.emptyList();
    }

    @Override
    public List<TypeDto> getAllType() throws Exception {
        List<Type> typeList = typeService.getAll(AssetType.FIXED);
        List<TypeDto> typeDtoList = modelMapper.map(typeList, new TypeToken<List<TypeDto>>() {
        }.getType());
        return (!typeDtoList.isEmpty()) ? typeDtoList : Collections.emptyList();
    }

    @Override
    public List<StatusDto> getAllStatus() throws Exception {
        List<Type> statusList = typeService.getAll(AssetType.FIXED);
        List<StatusDto> statusDtoList = modelMapper.map(statusList, new TypeToken<List<StatusDto>>() {
        }.getType());
        return (!statusDtoList.isEmpty()) ? statusDtoList : Collections.emptyList();
    }
}
