package com.lahiru.ims.feature.inventory.asset.network;


import com.lahiru.ims.common.controller.GenericAssetController;
import com.lahiru.ims.common.dto.PaginationResponse;
import com.lahiru.ims.common.dto.StandardReponse;
import com.lahiru.ims.feature.inventory.asset.network.dto.NetworkAssetRequestDto;
import com.lahiru.ims.feature.inventory.asset.network.dto.NetworkAssetResponseDto;
import com.lahiru.ims.feature.inventory.manufacturer.dto.ManufacturerDto;
import com.lahiru.ims.feature.inventory.model.dto.ModelDto;
import com.lahiru.ims.feature.inventory.status.dto.StatusDto;
import com.lahiru.ims.feature.inventory.type.dto.TypeDto;
import com.lahiru.ims.utils.ResponseEntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequestMapping("${endpoints.asset-network}")
@RestController
@RequiredArgsConstructor
public class NetworkAssetController implements GenericAssetController<NetworkAssetRequestDto, NetworkAssetResponseDto> {
    public static final String NETWORK_ASSET = "Network Asset";
    private final NetworkAssetService service;

    @Override
    public ResponseEntity<PaginationResponse<NetworkAssetResponseDto>> getAllByPageWise(int page, int pageSize) throws Exception {
        PaginationResponse<NetworkAssetResponseDto> networkByPageWise = service.findByPageWise(page, pageSize);
        return ResponseEntityManager.page(networkByPageWise);
    }

    @Override
    public ResponseEntity<StandardReponse<List<NetworkAssetResponseDto>>> getAll() throws Exception {
        List<NetworkAssetResponseDto> allNetwork = service.findAll();
        return ResponseEntityManager.ok(allNetwork);
    }

    @Override
    public ResponseEntity<StandardReponse<NetworkAssetResponseDto>> createOne(NetworkAssetRequestDto networkAssetRequestDto) throws Exception {
        NetworkAssetResponseDto network = service.createOne(networkAssetRequestDto);
        return ResponseEntityManager.created(network, NETWORK_ASSET);
    }

    @Override
    public ResponseEntity<StandardReponse<NetworkAssetResponseDto>> updateOne(int id, NetworkAssetRequestDto networkAssetRequestDto) throws Exception {
        NetworkAssetResponseDto networkAssetResponseDto = service.updateOne(id, networkAssetRequestDto);
        return ResponseEntityManager.updated(networkAssetResponseDto, NETWORK_ASSET);
    }

    @Override
    public ResponseEntity<StandardReponse<NetworkAssetResponseDto>> deleteOne(int id) throws Exception {
        NetworkAssetResponseDto networkAssetResponseDto = service.deleteOne(id);
        return ResponseEntityManager.deleted(networkAssetResponseDto, NETWORK_ASSET);
    }

    @Override
    public ResponseEntity<StandardReponse<List<ModelDto>>> getAllModels() throws Exception {
        List<ModelDto> modelList = service.getAllModels();
        return ResponseEntityManager.ok(modelList);
    }

    @Override
    public ResponseEntity<StandardReponse<List<TypeDto>>> getAllTypes() throws Exception {
        List<TypeDto> typeList = service.getAllTypes();
        return ResponseEntityManager.ok(typeList);
    }

    @Override
    public ResponseEntity<StandardReponse<List<StatusDto>>> getAllStatus() throws Exception {
        List<StatusDto> statusList = service.getAllStatus();
        return ResponseEntityManager.ok(statusList);
    }

    @Override
    public ResponseEntity<StandardReponse<List<ManufacturerDto>>> getAllManufacturer() throws Exception {
        List<ManufacturerDto> allManufacturers = service.getAllManufacturers();
        return ResponseEntityManager.ok(allManufacturers);
    }

    @Override
    public ResponseEntity<StandardReponse<List<NetworkAssetResponseDto>>> search(String key) throws Exception {
        List<NetworkAssetResponseDto> responseDtoList = service.search(key);
        return ResponseEntityManager.ok(responseDtoList);
    }

    @GetMapping("/pe-router/all")
    public ResponseEntity<StandardReponse<List<NetworkAssetResponseDto>>> getAllPERouters() throws Exception {
        List<NetworkAssetResponseDto> allPERouters = service.findAllPERouters();
        return ResponseEntityManager.ok(allPERouters);
    }

    @GetMapping("/switch/all")
    public ResponseEntity<StandardReponse<List<NetworkAssetResponseDto>>> getAllSwitches() throws Exception {
        List<NetworkAssetResponseDto> switchList = service.findAllSwitches();
        return ResponseEntityManager.ok(switchList);
    }

    @GetMapping("/router/all")
    public ResponseEntity<StandardReponse<List<NetworkAssetResponseDto>>> getAllRouters() throws Exception {
        List<NetworkAssetResponseDto> routersList = service.findAllRouters();
        return ResponseEntityManager.ok(routersList);
    }

    @GetMapping("/router/available")
    public ResponseEntity<StandardReponse<List<NetworkAssetResponseDto>>> getAvailableRouters() throws Exception {
        List<NetworkAssetResponseDto> routersList = service.findAvailableRouters();
        return ResponseEntityManager.ok(routersList);
    }

    @GetMapping("/switch/search")
    public ResponseEntity<StandardReponse<List<NetworkAssetResponseDto>>> searchSwitches(@RequestParam("key") String key) throws Exception {
        List<NetworkAssetResponseDto> switchList = service.searchSwitches(key);
        return ResponseEntityManager.ok(switchList);
    }

    @GetMapping("/router/search")
    public ResponseEntity<StandardReponse<List<NetworkAssetResponseDto>>> searchRouters(@RequestParam("key") String key) throws Exception {
        List<NetworkAssetResponseDto> routersList = service.searchRouters(key);
        return ResponseEntityManager.ok(routersList);
    }
}
