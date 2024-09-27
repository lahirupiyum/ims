package com.lahiru.ims.asset.network.model;

import com.lahiru.ims.asset.network.model.dto.DeviceModelRequestDto;
import com.lahiru.ims.asset.network.model.dto.DeviceModelResponseDto;
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
@RequestMapping("${endpoints.asset-network-device-model}")
@RequiredArgsConstructor
public class NetworkDeviceModelController implements GenericController<DeviceModelRequestDto, DeviceModelResponseDto> {
    private final NetworkDeviceModelService service;
    @Override
    public ResponseEntity<PaginationResponse<DeviceModelResponseDto>> getAllByPageWise(int page, int pageSize) throws Exception {
        PaginationResponse<DeviceModelResponseDto> deviceModelPage = service.findByPageWise(page, pageSize);
        return ResponseEntityManager.page(deviceModelPage);
    }

    @Override
    public ResponseEntity<StandardReponse<List<DeviceModelResponseDto>>> getAll() throws Exception {
        List<DeviceModelResponseDto> deviceModelList = service.findAll();
        return ResponseEntityManager.ok(deviceModelList);
    }

    @Override
    public ResponseEntity<StandardReponse<DeviceModelResponseDto>> createOne(DeviceModelRequestDto requestDto) throws Exception {
        DeviceModelResponseDto deviceModel = service.createOne(requestDto);
        return ResponseEntityManager.created(deviceModel, "Network device model");
    }

    @Override
    public ResponseEntity<StandardReponse<DeviceModelResponseDto>> updateOne(int id, DeviceModelRequestDto requestDto) throws Exception {
        DeviceModelResponseDto deviceModel = service.updateOne(id, requestDto);
        return ResponseEntityManager.ok(deviceModel);
    }

    @Override
    public ResponseEntity<StandardReponse<DeviceModelResponseDto>> deleteOne(int id) throws Exception {
        DeviceModelResponseDto deviceModel = service.deleteOne(id);
        return ResponseEntityManager.ok(deviceModel);
    }
}
