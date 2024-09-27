package com.lahiru.ims.asset.network.device;

import com.lahiru.ims.asset.network.device.dto.NetworkDeviceResponseDto;
import com.lahiru.ims.asset.network.manufacturer.NetworkDeviceManufacturerMapper;
import com.lahiru.ims.asset.network.model.NetworkDeviceModelMapper;
import com.lahiru.ims.asset.network.status.NetworkDeviceStatusMapper;
import com.lahiru.ims.asset.network.type.NetworkDeviceTypeMapper;
import com.lahiru.ims.branch.BranchMapper;
import com.lahiru.ims.vendor.VendorMapper;
import org.springframework.stereotype.Component;
import com.lahiru.ims.asset.network.device.dto.NetworkDeviceRequestDto;
import com.lahiru.ims.asset.network.manufacturer.NetworkDeviceManufacturer;
import com.lahiru.ims.asset.network.manufacturer.NetworkDeviceManufacturerRepo;
import com.lahiru.ims.asset.network.model.NetworkDeviceModel;
import com.lahiru.ims.asset.network.model.NetworkDeviceModelRepo;
import com.lahiru.ims.asset.network.status.NetworkDeviceStatus;
import com.lahiru.ims.asset.network.status.NetworkDeviceStatusRepo;
import com.lahiru.ims.asset.network.type.NetworkDeviceType;
import com.lahiru.ims.asset.network.type.NetworkDeviceTypeRepo;
import com.lahiru.ims.branch.Branch;
import com.lahiru.ims.branch.BranchRepo;
import com.lahiru.ims.exception.NotFoundException;
import com.lahiru.ims.vendor.Vendor;
import com.lahiru.ims.vendor.VendorRepo;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class NetworkDeviceMapper {
    private final BranchRepo branchRepo;
    private final VendorRepo vendorRepo;
    private final NetworkDeviceManufacturerRepo manufacturerRepo;
    private final NetworkDeviceStatusRepo statusRepo;
    private final NetworkDeviceModelRepo modelRepo;
    private final NetworkDeviceTypeRepo typeRepo;


    public NetworkDevice toModel(NetworkDeviceRequestDto requestDto) {
        Branch branch = branchRepo.findActiveById(requestDto.getBranchId())
                .orElseThrow(() -> new NotFoundException("Branch"));
        Vendor vendor = vendorRepo.findByIdAndIsActive(requestDto.getVendorId(), true)
                .orElseThrow(() -> new NotFoundException("Vendor"));
        NetworkDeviceManufacturer manufacturer = manufacturerRepo.findActiveById(requestDto.getManufacturerId())
                .orElseThrow(() -> new NotFoundException("Device Manufacturer"));
        NetworkDeviceStatus status = statusRepo.findById(requestDto.getStatusId())
                .orElseThrow(() -> new NotFoundException("Device Status"));
        NetworkDeviceModel model = modelRepo.findByIdAndIsActive(requestDto.getModelId(), true)
                .orElseThrow(() -> new NotFoundException("Device Model"));
        NetworkDeviceType type = typeRepo.findByIdAndIsActive(requestDto.getTypeId(), true)
                .orElseThrow(() -> new NotFoundException("Device Type"));

        return new NetworkDevice(
                requestDto.getSerialNumber(),
                requestDto.getQuantity(),
                type,
                manufacturer,
                model,
                status,
                branch,
                vendor
        );
    }

    public NetworkDeviceResponseDto toDto(NetworkDevice networkDevice) {
        return NetworkDeviceResponseDto.builder()
                .id(networkDevice.getId())
                .serialNumber(networkDevice.getSerialNumber())
                .quantity(networkDevice.getQuantity())
                .type(NetworkDeviceTypeMapper.toDto(networkDevice.getType()))
                .manufacturer(NetworkDeviceManufacturerMapper.toDto(networkDevice.getManufacturer()))
                .model(NetworkDeviceModelMapper.toDto(networkDevice.getModel()))
                .status(NetworkDeviceStatusMapper.toDto(networkDevice.getStatus()))
                .branch(BranchMapper.toDto(networkDevice.getBranch()))
                .vendor(VendorMapper.toDto(networkDevice.getVendor()))
                .build();
    }
}
