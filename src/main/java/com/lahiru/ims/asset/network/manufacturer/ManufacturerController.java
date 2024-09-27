package com.lahiru.ims.asset.network.manufacturer;

import com.lahiru.ims.asset.network.manufacturer.dto.ManufacturerRequestDto;
import com.lahiru.ims.asset.network.manufacturer.dto.ManufacturerResponseDto;
import com.lahiru.ims.asset.network.manufacturer.impl.ManufacturerServiceImpl;
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
public class ManufacturerController implements GenericController<ManufacturerRequestDto, ManufacturerResponseDto> {

    private final NetworkDeviceManufacturerService manufacturerService;

    @Override
    public ResponseEntity<PaginationResponse<ManufacturerResponseDto>> getAllByPageWise(int page, int pageSize) throws Exception {
        PaginationResponse<ManufacturerResponseDto> allByPageWise = manufacturerService.findByPageWise(page, pageSize);
        return ResponseEntityManager.getPaginationResponse(allByPageWise);
    }

    @Override
    public ResponseEntity<StandardReponse<List<ManufacturerResponseDto>>> getAll() throws Exception {
        List<ManufacturerResponseDto> all = manufacturerService.findAll();
        return ResponseEntityManager.getOkResponse(all);
    }

    @Override
    public ResponseEntity<StandardReponse<ManufacturerResponseDto>> createOne(ManufacturerRequestDto manufacturerRequestDto) throws Exception {
        ManufacturerResponseDto createdManufacturer = manufacturerService.createOne(manufacturerRequestDto);
        return ResponseEntityManager.getCreateResponse(createdManufacturer, "Network Device Manufacturer");
    }

    @Override
    public ResponseEntity<StandardReponse<ManufacturerResponseDto>> updateOne(int id, ManufacturerRequestDto manufacturerRequestDto) throws Exception {
        ManufacturerResponseDto updatedManufacturer = manufacturerService.updateOne(id, manufacturerRequestDto);
        return ResponseEntityManager.getOkResponse(updatedManufacturer);
    }

    @Override
    public ResponseEntity<StandardReponse<ManufacturerResponseDto>> deleteOne(int id) throws Exception {
        ManufacturerResponseDto deletedManufacturer = manufacturerService.deleteOne(id);
        return ResponseEntityManager.getOkResponse(deletedManufacturer);
    }
}
