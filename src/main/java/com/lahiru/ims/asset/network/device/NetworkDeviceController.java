package com.lahiru.ims.asset.network.device;

import com.lahiru.ims.asset.network.device.dto.NetworkDeviceRequestDto;
import com.lahiru.ims.asset.network.device.dto.NetworkDeviceResponseDto;
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
@RequestMapping("${endpoints.asset-network-device}")
@RequiredArgsConstructor
public class NetworkDeviceController implements GenericController<NetworkDeviceRequestDto, NetworkDeviceResponseDto> {

    private final NetworkDeviceService networkDeviceService;

    @Override
    public ResponseEntity<PaginationResponse<NetworkDeviceResponseDto>> getAllByPageWise(int page, int pageSize) throws Exception {
        PaginationResponse<NetworkDeviceResponseDto> networkDevicePage = networkDeviceService.findByPageWise(page, pageSize);
        return ResponseEntityManager.page(networkDevicePage);
    }

    @Override
    public ResponseEntity<StandardReponse<List<NetworkDeviceResponseDto>>> getAll() throws Exception {
        List<NetworkDeviceResponseDto> networkDeviceList = networkDeviceService.findAll();
        return ResponseEntityManager.ok(networkDeviceList);
    }

    @Override
    public ResponseEntity<StandardReponse<NetworkDeviceResponseDto>> createOne(NetworkDeviceRequestDto networkDeviceRequestDto) throws Exception {
        NetworkDeviceResponseDto createdNetworkDevice = networkDeviceService.createOne(networkDeviceRequestDto);
        return ResponseEntityManager.created(createdNetworkDevice, "Network Device");
    }

    @Override
    public ResponseEntity<StandardReponse<NetworkDeviceResponseDto>> updateOne(int id, NetworkDeviceRequestDto networkDeviceRequestDto) throws Exception {
        NetworkDeviceResponseDto updatedNetworkDevice = networkDeviceService.updateOne(id, networkDeviceRequestDto);
        return ResponseEntityManager.ok(updatedNetworkDevice);
    }

    @Override
    public ResponseEntity<StandardReponse<NetworkDeviceResponseDto>> deleteOne(int id) throws Exception {
        NetworkDeviceResponseDto deletedNetworkDevice = networkDeviceService.deleteOne(id);
        return ResponseEntityManager.ok(deletedNetworkDevice);
    }
}
