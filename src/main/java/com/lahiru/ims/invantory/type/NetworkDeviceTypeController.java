package com.lahiru.ims.invantory.type;

import com.lahiru.ims.common.GenericController;
import com.lahiru.ims.common.dto.PaginationResponse;
import com.lahiru.ims.common.dto.StandardReponse;
import com.lahiru.ims.invantory.type.dto.NetworkDeviceTypeRequestDto;
import com.lahiru.ims.invantory.type.dto.NetworkDeviceTypeResponseDto;
import com.lahiru.ims.utils.ResponseEntityManager;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("${endpoints.asset-network-device-type}")
@RequiredArgsConstructor
public class NetworkDeviceTypeController implements GenericController<NetworkDeviceTypeRequestDto, NetworkDeviceTypeResponseDto> {

    private final NetworkDeviceTypeService networkDeviceTypeService;

    @Override
    public ResponseEntity<PaginationResponse<NetworkDeviceTypeResponseDto>> getAllByPageWise(int page, int pageSize) throws Exception {
        PaginationResponse<NetworkDeviceTypeResponseDto> all = networkDeviceTypeService.findByPageWise(page, pageSize);
        return ResponseEntityManager.page(all);
    }

    @Override
    public ResponseEntity<StandardReponse<List<NetworkDeviceTypeResponseDto>>> getAll() throws Exception {
        List<NetworkDeviceTypeResponseDto> all = networkDeviceTypeService.findAll();
        return ResponseEntityManager.ok(all);
    }

    @Override
    public ResponseEntity<StandardReponse<NetworkDeviceTypeResponseDto>> createOne(
            @Valid NetworkDeviceTypeRequestDto requestDto) throws Exception {
        NetworkDeviceTypeResponseDto typeResponseDto = networkDeviceTypeService.createOne(requestDto);
        return ResponseEntityManager.created(typeResponseDto, "Network Device Type");
    }

    @Override
    public ResponseEntity<StandardReponse<NetworkDeviceTypeResponseDto>> updateOne(int id,
            @Valid NetworkDeviceTypeRequestDto requestDto) throws Exception {
        NetworkDeviceTypeResponseDto updatedNetworkDeviceType = networkDeviceTypeService.updateOne(id, requestDto);
        return ResponseEntityManager.ok(updatedNetworkDeviceType);
    }

    @Override
    public ResponseEntity<StandardReponse<NetworkDeviceTypeResponseDto>> deleteOne(int id) throws Exception {
        NetworkDeviceTypeResponseDto deletedNetworkDeviceType = networkDeviceTypeService.deleteOne(id);
        return ResponseEntityManager.ok(deletedNetworkDeviceType);
    }


}
