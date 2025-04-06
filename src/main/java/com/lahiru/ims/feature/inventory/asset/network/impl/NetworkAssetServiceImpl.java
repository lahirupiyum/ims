package com.lahiru.ims.feature.inventory.asset.network.impl;


import com.lahiru.ims.common.GenericDao;
import com.lahiru.ims.common.dto.PaginationResponse;
import com.lahiru.ims.common.enums.AssetType;
import com.lahiru.ims.exception.NotFoundException;
import com.lahiru.ims.exception.ValidationException;
import com.lahiru.ims.feature.customer.service.connection.ConnectionRepo;
import com.lahiru.ims.feature.inventory.asset.network.NetworkAsset;
import com.lahiru.ims.feature.inventory.asset.network.NetworkAssetRepo;
import com.lahiru.ims.feature.inventory.asset.network.NetworkAssetService;
import com.lahiru.ims.feature.inventory.asset.network.dto.NetworkAssetRequestDto;
import com.lahiru.ims.feature.inventory.asset.network.dto.NetworkAssetResponseDto;
import com.lahiru.ims.feature.inventory.location.Location;
import com.lahiru.ims.feature.inventory.location.LocationService;
import com.lahiru.ims.feature.inventory.manufacturer.Manufacturer;
import com.lahiru.ims.feature.inventory.manufacturer.ManufacturerService;
import com.lahiru.ims.feature.inventory.manufacturer.dto.ManufacturerDto;
import com.lahiru.ims.feature.inventory.model.Model;
import com.lahiru.ims.feature.inventory.model.ModelService;
import com.lahiru.ims.feature.inventory.model.dto.ModelDto;
import com.lahiru.ims.feature.inventory.status.Status;
import com.lahiru.ims.feature.inventory.status.StatusService;
import com.lahiru.ims.feature.inventory.status.dto.StatusDto;
import com.lahiru.ims.feature.inventory.status.enums.NetworkAssetStatus;
import com.lahiru.ims.feature.inventory.type.Type;
import com.lahiru.ims.feature.inventory.type.TypeService;
import com.lahiru.ims.feature.inventory.type.dto.TypeDto;
import com.lahiru.ims.feature.inventory.type.enums.NetworkAssetType;
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
public class NetworkAssetServiceImpl implements NetworkAssetService {
    private final String NETWORK = "Network Asset";
    private final ModelMapper modelMapper;
    private final ManufacturerService manufacturerService;
    private final TypeService typeService;
    private final ModelService modelService;
    private final StatusService statusService;
    private final VendorService vendorService;
    private final LocationService locationService;
    private final GenericDao genericDao;
    private final NetworkAssetRepo networkAssetRepo;
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final ConnectionRepo connectionRepo;

    @Override
    public PaginationResponse<NetworkAssetResponseDto> findByPageWise(int page, int pageSize) throws Exception {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<NetworkAsset> networkPage = networkAssetRepo.findAllByPageWise(pageable);
        try {
            List<NetworkAssetResponseDto> responseDtoList = modelMapper.map(networkPage.stream().toList(), new TypeToken<List<NetworkAssetResponseDto>>() {
            }.getType());
            return new PaginationResponse<>(responseDtoList, (int) networkPage.getTotalElements());
        } catch (Exception e) {
            log.info("Model Mapper Error Generate From findByPageWise");
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<NetworkAssetResponseDto> findAll() throws Exception {
        List<NetworkAsset> networkAssetList = networkAssetRepo.findAllActive();
        try {
            List<NetworkAssetResponseDto> responseNetworks = modelMapper.map(networkAssetList, new TypeToken<List<NetworkAssetResponseDto>>() {
            }.getType());
            return (!responseNetworks.isEmpty()) ? responseNetworks : Collections.emptyList();
        } catch (Exception e) {
            log.info("Model Mapper Error Generate By Find All {}", NETWORK);
            throw new Exception(e);
        }
    }

    @Override
    public NetworkAssetResponseDto createOne(NetworkAssetRequestDto networkAssetRequestDto) throws Exception {
        NetworkAsset toSave = convertToModel(networkAssetRequestDto);
        NetworkAsset savedNetworkAsset = networkAssetRepo.save(toSave);
        log.info("Saved NETWORK Asset !");
        return convertToDto(savedNetworkAsset);
    }

    @Override
    public NetworkAssetResponseDto updateOne(int id, NetworkAssetRequestDto networkAssetRequestDto) throws Exception {
        String validationError = "You cannot update status or type for this asset!";
        NetworkAsset foundNetworkAsset = networkAssetRepo.findActiveOne(id).orElseThrow(() -> new NotFoundException(NETWORK));

        Boolean isConnectionActiveByAsset = connectionRepo.isActiveConnectionExistsByNetworkAsset(foundNetworkAsset);
        if (isConnectionActiveByAsset) throw new ValidationException(validationError);

        NetworkAsset updatedNetworkAsset = convertToModel(networkAssetRequestDto);
        updatedNetworkAsset.setId(foundNetworkAsset.getId());
        NetworkAsset saveUpdated = networkAssetRepo.saveAndFlush(updatedNetworkAsset);
        return convertToDto(saveUpdated);

    }

    @Override
    public NetworkAssetResponseDto deleteOne(int id) throws Exception {
        NetworkAsset networkAsset = networkAssetRepo.findActiveOne(id).orElseThrow(() -> new NotFoundException(NETWORK));
        Boolean activeConnectionExistsByNetworkAsset = connectionRepo.isActiveConnectionExistsByNetworkAsset(networkAsset);
        if (activeConnectionExistsByNetworkAsset) throw new ValidationException("This asset has been linked to active connection");
        networkAsset.setIsActive(false);
        networkAssetRepo.save(networkAsset);
        log.info("Deleted Successful id:{}", networkAsset.getId());
        return convertToDto(networkAsset);
    }

    @Override
    public NetworkAsset convertToModel(NetworkAssetRequestDto networkAssetRequestDto) throws Exception {
        Manufacturer manufacturer = genericDao.checkAndCreate(AssetType.NETWORK, networkAssetRequestDto.getManufacturer(), manufacturerService);
        Type type = genericDao.checkAndCreate(AssetType.NETWORK, networkAssetRequestDto.getType(), typeService);
        Model model = genericDao.checkAndCreate(AssetType.NETWORK, networkAssetRequestDto.getModel(), modelService);
        Status status = statusService.findOne(networkAssetRequestDto.getStatusId());
        Location location = locationService.findOne(networkAssetRequestDto.getLocationId());
        Vendor vendor = vendorService.findOne(networkAssetRequestDto.getVendorId());
        try {
            ModelMapper modelMapper1 = new ModelMapper();
            modelMapper1.addMappings(new PropertyMap<NetworkAssetRequestDto, NetworkAsset>() {
                @Override
                protected void configure() {
                    skip(destination.getId());
                }
            });
            NetworkAsset networkAsset = modelMapper1.map(networkAssetRequestDto, NetworkAsset.class);
            networkAsset.setManufacturer(manufacturer);
            networkAsset.setType(type);
            networkAsset.setModel(model);
            networkAsset.setStatus(status);
            networkAsset.setLocation(location);
            networkAsset.setVendor(vendor);
            networkAsset.setIsActive(true);
            return networkAsset;
        } catch (Exception e) {
            log.error("Model Mapper Converting Error Where {} -> {} ", NETWORK, "ConvertDtoToEntity");
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public NetworkAssetResponseDto convertToDto(NetworkAsset networkAsset) {
        try {
            return modelMapper.map(networkAsset, NetworkAssetResponseDto.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Get Model , Type ,Status
    @Override
    public List<ModelDto> getAllModels() throws Exception {
        List<Model> modelList = modelService.getAll(AssetType.NETWORK);
        List<ModelDto> modelDtoList = modelMapper.map(modelList, new TypeToken<List<ModelDto>>() {
        }.getType());
        return (!modelDtoList.isEmpty()) ? modelDtoList : Collections.emptyList();
    }

    @Override
    public List<TypeDto> getAllTypes() throws Exception {
        List<Type> typeList = typeService.getAll(AssetType.NETWORK);
        List<TypeDto> typeDtoList = modelMapper.map(typeList, new TypeToken<List<TypeDto>>() {
        }.getType());
        return (!typeDtoList.isEmpty()) ? typeDtoList : Collections.emptyList();
    }

    @Override
    public List<StatusDto> getAllStatus() throws Exception {
        List<Status> statusList = statusService.getAll(AssetType.NETWORK);
        List<StatusDto> statusDtoList = modelMapper.map(statusList, new TypeToken<List<StatusDto>>() {
        }.getType());
        return (!statusDtoList.isEmpty()) ? statusDtoList : Collections.emptyList();
    }

    @Override
    public List<ManufacturerDto> getAllManufacturers() throws Exception {
        List<Manufacturer> all = manufacturerService.getAll(AssetType.NETWORK);
        List<ManufacturerDto> manufacturerDtoList = modelMapper.map(all, new TypeToken<List<ManufacturerDto>>() {
        }.getType());
        return (!manufacturerDtoList.isEmpty()) ? manufacturerDtoList : Collections.emptyList();
    }

    @Override
    public List<NetworkAssetResponseDto> search(String key) throws Exception {
        List<NetworkAsset> networkAssetList = networkAssetRepo.search(key);
        return networkAssetList.stream().map(this::convertToDto).toList();
    }

    @Override
    public NetworkAsset findOne(Integer id) throws Exception {
        return networkAssetRepo.findActiveOne(id).orElseThrow(() -> new NotFoundException(NETWORK));
    }

    @Override
    public List<NetworkAssetResponseDto> findAllPERouters() throws Exception {
        Type peRouterType = getRequiredType(NetworkAssetType.PROVIDER_EDGE_ROUTER);
        List<NetworkAsset> allByType = networkAssetRepo.findAllByType(peRouterType);
        return allByType.stream().map(this::convertToDto).toList();
    }

    @Override
    public List<NetworkAssetResponseDto> findAllSwitches() throws Exception {
        Type swtichType = getRequiredType(NetworkAssetType.SWITCH);
        List<NetworkAsset> allByType = networkAssetRepo.findAllByType(swtichType);
        return allByType.stream().map(this::convertToDto).toList();
    }

    @Override
    public List<NetworkAssetResponseDto> findAllRouters() throws Exception {
        Type swtichType = getRequiredType(NetworkAssetType.ROUTER);
        List<NetworkAsset> allByType = networkAssetRepo.findAllByType(swtichType);
        return allByType.stream().map(this::convertToDto).toList();
    }

    @Override
    public List<NetworkAssetResponseDto> searchSwitches(String serialNumber) throws Exception {
        return searchNetworkAssets(NetworkAssetType.SWITCH, serialNumber);
    }

    @Override
    public List<NetworkAssetResponseDto> searchRouters(String serialNumber) throws Exception {
        Status availableStatus = statusService.getByStatus(AssetType.NETWORK, NetworkAssetStatus.AVAILABLE);
        List<NetworkAsset> networkAssetList = networkAssetRepo.searchByTypeAndStatus(getRequiredType(NetworkAssetType.ROUTER), availableStatus, serialNumber);
        return networkAssetList.stream().map(this::convertToDto).toList();
    }

    @Override
    public List<NetworkAssetResponseDto> findAvailableRouters() throws Exception {
        Status availableStatus = statusService.getByStatus(AssetType.NETWORK, NetworkAssetStatus.AVAILABLE);
        List<NetworkAsset> networkRouterList = networkAssetRepo.findByTypeAndStatus(getRequiredType(NetworkAssetType.ROUTER), availableStatus);
        return networkRouterList.stream().map(this::convertToDto).toList();
    }

    @Override
    public NetworkAsset updateAssetStatus(Integer id, NetworkAssetStatus status) throws Exception {
        NetworkAsset networkAsset = findOne(id);
        return updateAssetStatus(networkAsset, status);
    }

    @Override
    public NetworkAsset updateAssetStatus(NetworkAsset networkAsset, NetworkAssetStatus status) throws Exception {
        Boolean isConnectionActiveByAsset = connectionRepo.isActiveConnectionExistsByNetworkAsset(networkAsset);
        if (isConnectionActiveByAsset && status != NetworkAssetStatus.AVAILABLE)
            throw new ValidationException("This asset is combined with a active service connection");

        List<Status> all = statusService.getAll(AssetType.NETWORK);
        Status networkStatus = all.stream().filter(networkAssetStatus -> networkAssetStatus.getName().equals(status.getDisplayName())).findAny()
                // if unable to find required type, there might be issue with the data seeder
                .orElseThrow(() -> new NotFoundException("Unable to find required status due to Server Error", true));

        networkAsset.setStatus(networkStatus);
        return networkAssetRepo.save(networkAsset);
    }

    private List<NetworkAssetResponseDto> searchNetworkAssets(NetworkAssetType networkAssetAssetTypeType, String serialNumber) throws Exception {
        Type requiredType = getRequiredType(networkAssetAssetTypeType);
        return networkAssetRepo.searchByTypeAndSerialNumber(requiredType, serialNumber).stream().map(this::convertToDto).toList();
    }

    private Type getRequiredType(NetworkAssetType networkAssetAssetTypeType) throws Exception {
        List<Type> all = typeService.getAll(AssetType.NETWORK);
        // if unable to find the required type, there might be issue with the data seeder
        return all.stream().filter(type -> type.getName().equalsIgnoreCase(networkAssetAssetTypeType.getDisplayName())).findAny().orElseThrow(() -> new NotFoundException("PE Router Type"));
    }

}
