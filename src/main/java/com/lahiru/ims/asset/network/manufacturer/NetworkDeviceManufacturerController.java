package com.lahiru.ims.asset.network.manufacturer;

import com.lahiru.ims.asset.network.manufacturer.dto.NetworkDeviceManufacturerRequestDto;
import com.lahiru.ims.asset.network.manufacturer.dto.NetworkDeviceManufacturerResponseDto;
import com.lahiru.ims.common.GenericController;
import com.lahiru.ims.common.ResponseEntityManager;
import com.lahiru.ims.common.dto.PaginationResponse;
import com.lahiru.ims.common.dto.StandardReponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("${endpoints.asset-network-device-manufacturer}")
@RequiredArgsConstructor
public class NetworkDeviceManufacturerController implements GenericController<NetworkDeviceManufacturerRequestDto, NetworkDeviceManufacturerResponseDto> {

    private final NetworkDeviceManufacturerService manufacturerService;

    @Override
    public ResponseEntity<PaginationResponse<NetworkDeviceManufacturerResponseDto>> getAllByPageWise(int page, int pageSize) throws Exception {
        PaginationResponse<NetworkDeviceManufacturerResponseDto> allByPageWise = manufacturerService.findByPageWise(page, pageSize);
        return ResponseEntityManager.page(allByPageWise);
    }

    @Override
    public ResponseEntity<StandardReponse<List<NetworkDeviceManufacturerResponseDto>>> getAll() throws Exception {
        List<NetworkDeviceManufacturerResponseDto> all = manufacturerService.findAll();
        return ResponseEntityManager.ok(all);
    }

    @Override
    public ResponseEntity<StandardReponse<NetworkDeviceManufacturerResponseDto>> createOne(NetworkDeviceManufacturerRequestDto networkDeviceManufacturerRequestDto) throws Exception {
        NetworkDeviceManufacturerResponseDto createdManufacturer = manufacturerService.createOne(networkDeviceManufacturerRequestDto);
        return ResponseEntityManager.created(createdManufacturer, "Network Device Manufacturer");
    }

    @Override
    public ResponseEntity<StandardReponse<NetworkDeviceManufacturerResponseDto>> updateOne(int id, NetworkDeviceManufacturerRequestDto networkDeviceManufacturerRequestDto) throws Exception {
        NetworkDeviceManufacturerResponseDto updatedManufacturer = manufacturerService.updateOne(id, networkDeviceManufacturerRequestDto);
        return ResponseEntityManager.ok(updatedManufacturer);
    }

    @Override
    public ResponseEntity<StandardReponse<NetworkDeviceManufacturerResponseDto>> deleteOne(int id) throws Exception {
        NetworkDeviceManufacturerResponseDto deletedManufacturer = manufacturerService.deleteOne(id);
        return ResponseEntityManager.ok(deletedManufacturer);
    }
}
