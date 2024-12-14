package com.lahiru.ims.features.inventory.asset.network.model;

import com.lahiru.ims.common.GenericController;
import com.lahiru.ims.common.dto.PaginationResponse;
import com.lahiru.ims.common.dto.StandardReponse;
import com.lahiru.ims.features.inventory.asset.network.model.dto.NetworkDeviceModelRequestDto;
import com.lahiru.ims.features.inventory.asset.network.model.dto.NetworkDeviceModelResponseDto;
import com.lahiru.ims.utils.ResponseEntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("${endpoints.asset-network-device-model}")
@RequiredArgsConstructor
public class NetworkDeviceModelController implements GenericController<NetworkDeviceModelRequestDto, NetworkDeviceModelResponseDto> {
    private final NetworkDeviceModelService service;
    @Override
    public ResponseEntity<PaginationResponse<NetworkDeviceModelResponseDto>> getAllByPageWise(int page, int pageSize) throws Exception {
        PaginationResponse<NetworkDeviceModelResponseDto> deviceModelPage = service.findByPageWise(page, pageSize);
        return ResponseEntityManager.page(deviceModelPage);
    }

    @Override
    public ResponseEntity<StandardReponse<List<NetworkDeviceModelResponseDto>>> getAll() throws Exception {
        List<NetworkDeviceModelResponseDto> deviceModelList = service.findAll();
        return ResponseEntityManager.ok(deviceModelList);
    }

    @Override
    public ResponseEntity<StandardReponse<NetworkDeviceModelResponseDto>> createOne(NetworkDeviceModelRequestDto requestDto) throws Exception {
        NetworkDeviceModelResponseDto deviceModel = service.createOne(requestDto);
        return ResponseEntityManager.created(deviceModel, "Network device model");
    }

    @Override
    public ResponseEntity<StandardReponse<NetworkDeviceModelResponseDto>> updateOne(int id, NetworkDeviceModelRequestDto requestDto) throws Exception {
        NetworkDeviceModelResponseDto deviceModel = service.updateOne(id, requestDto);
        return ResponseEntityManager.ok(deviceModel);
    }

    @Override
    public ResponseEntity<StandardReponse<NetworkDeviceModelResponseDto>> deleteOne(int id) throws Exception {
        NetworkDeviceModelResponseDto deviceModel = service.deleteOne(id);
        return ResponseEntityManager.ok(deviceModel);
    }
}
