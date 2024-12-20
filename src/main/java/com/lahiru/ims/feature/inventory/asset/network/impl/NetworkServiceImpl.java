package com.lahiru.ims.feature.inventory.asset.network.impl;


import com.lahiru.ims.common.GenericDao;
import com.lahiru.ims.common.dto.PaginationResponse;
import com.lahiru.ims.common.enums.AssetType;
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
import com.lahiru.ims.feature.inventory.status.Status;
import com.lahiru.ims.feature.inventory.status.StatusService;
import com.lahiru.ims.feature.inventory.type.Type;
import com.lahiru.ims.feature.inventory.type.TypeService;
import com.lahiru.ims.feature.inventory.vendor.Vendor;
import com.lahiru.ims.feature.inventory.vendor.VendorService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NetworkServiceImpl implements NetworkService {
    private final NetworkRepo repository;
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

    @Override
    public PaginationResponse<NetworkAssetResponseDto> findByPageWise(int page, int pageSize) throws Exception {
        return null;
    }

    @Override
    public List<NetworkAssetResponseDto> findAll() throws Exception {

        return List.of();
    }

    @Override
    public NetworkAssetResponseDto createOne(NetworkAssetRequestDto networkAssetRequestDto) throws Exception {
        Manufacturer manufacturer = genericDao.checkAndCreate(AssetType.NETWORK, networkAssetRequestDto.getManufacturer(), manufacturerService);
        Type type = genericDao.checkAndCreate(AssetType.NETWORK, networkAssetRequestDto.getType(), typeService);
        Model model = genericDao.checkAndCreate(AssetType.NETWORK, networkAssetRequestDto.getModel(), modelService);
        Status status = statusService.findOne(networkAssetRequestDto.getStatusId());
        Location location = locationService.findOne(networkAssetRequestDto.getLocationId());
        Vendor vendor = vendorService.findOne(networkAssetRequestDto.getVendorId());
       try{
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
           Network savedNetwork = networkRepo.save(network);
           return modelMapper.map(savedNetwork,NetworkAssetResponseDto.class);
       }catch (Exception e){

           throw new RuntimeException(e.getMessage());
       }
    }

    @Override
    public NetworkAssetResponseDto updateOne(int id, NetworkAssetRequestDto networkAssetRequestDto) throws Exception {
        return null;
    }

    @Override
    public NetworkAssetResponseDto deleteOne(int id) throws Exception {
        return null;
    }
}
