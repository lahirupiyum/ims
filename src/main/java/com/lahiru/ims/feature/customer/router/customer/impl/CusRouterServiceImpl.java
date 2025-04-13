package com.lahiru.ims.feature.customer.router.customer.impl;

import com.lahiru.ims.common.dto.PaginationResponse;
import com.lahiru.ims.exception.NotFoundException;
import com.lahiru.ims.feature.customer.router.customer.CusRouter;
import com.lahiru.ims.feature.customer.router.customer.CusRouterRepo;
import com.lahiru.ims.feature.customer.router.customer.CusRouterService;
import com.lahiru.ims.feature.customer.router.customer.dto.CusRouterRequestDto;
import com.lahiru.ims.feature.customer.router.customer.dto.CusRouterResponseDto;
import com.lahiru.ims.feature.inventory.asset.network.NetworkAsset;
import com.lahiru.ims.feature.inventory.asset.network.NetworkAssetService;
import com.lahiru.ims.feature.inventory.status.enums.NetworkAssetStatus;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@Transactional
@RequiredArgsConstructor
public class CusRouterServiceImpl implements CusRouterService {
    public static final String CUSTOMER_ROUTER = "Customer Router";
    private final CusRouterRepo cusRouterRepo;
    private final ModelMapper modelMapper;
    private final NetworkAssetService networkAssetService;

    @Override
    public PaginationResponse<CusRouterResponseDto> findByPageWise(int page, int pageSize) throws Exception {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<CusRouter> allByPageWise = cusRouterRepo.findAllByPageWise(pageable);
        int totalCount = (int) allByPageWise.getTotalElements();
        List<CusRouterResponseDto> all = allByPageWise.map(this::convertToDto).stream().toList();
        return new PaginationResponse<>(all, totalCount);
    }

    @Override
    public List<CusRouterResponseDto> findAll() throws Exception {
        return cusRouterRepo.findAllActive().stream().map(this::convertToDto).toList();
    }

    @Override
    public CusRouterResponseDto createOne(CusRouterRequestDto cusRouterRequestDto) throws Exception {
        CusRouter cusRouter = convertToModel(cusRouterRequestDto);

        NetworkAsset updatedNetworkAssetAsset = networkAssetService.updateAssetStatus(cusRouterRequestDto.getAssetId(), NetworkAssetStatus.RENTED);
        cusRouter.setNetworkAsset(updatedNetworkAssetAsset);
        CusRouter savedRouter = cusRouterRepo.save(cusRouter);
        return convertToDto(savedRouter);
    }

    @Override
    public CusRouterResponseDto updateOne(int id, CusRouterRequestDto cusRouterRequestDto) throws Exception {
        CusRouter cusRouter = cusRouterRepo.findActiveOne(id).orElseThrow(() -> new NotFoundException(CUSTOMER_ROUTER));
        CusRouter toUpdate = convertToModel(cusRouterRequestDto);

        if (!Objects.equals(toUpdate.getNetworkAsset().getId(), cusRouter.getNetworkAsset().getId()))
            networkAssetService.updateAssetStatus(cusRouter.getNetworkAsset().getId(), NetworkAssetStatus.AVAILABLE);

        NetworkAsset networkAsset;
        if (Objects.equals(cusRouter.getNetworkAsset().getId(), cusRouterRequestDto.getAssetId()))
            networkAsset = networkAssetService.findOne(cusRouterRequestDto.getAssetId());
        else
            networkAsset = networkAssetService.updateAssetStatus(cusRouterRequestDto.getAssetId(), NetworkAssetStatus.RENTED);

        toUpdate.setNetworkAsset(networkAsset);

        toUpdate.setId(id);
        CusRouter updatedRouter = cusRouterRepo.save(toUpdate);
        return convertToDto(updatedRouter);
    }

    @Override
    public CusRouterResponseDto deleteOne(int id) throws Exception {
        CusRouter cusRouter = cusRouterRepo.findActiveOne(id).orElseThrow(() -> new NotFoundException(CUSTOMER_ROUTER));
        cusRouter.setIsActive(false);
        NetworkAsset networkAsset = cusRouter.getNetworkAsset();
        NetworkAsset updatedAsset = networkAssetService.updateAssetStatus(networkAsset, NetworkAssetStatus.AVAILABLE);
        cusRouter.setNetworkAsset(updatedAsset);

        CusRouter deletedRouter = cusRouterRepo.save(cusRouter);
        return convertToDto(deletedRouter);
    }

    @SneakyThrows
    @Override
    public CusRouter convertToModel(CusRouterRequestDto cusRouterRequestDto) {
        CusRouter cusRouter = new CusRouter();
        cusRouter.setIsActive(true);

        cusRouter.setWanIpAddress(cusRouterRequestDto.getWanIpAddress());
        cusRouter.setLanIpPool(cusRouterRequestDto.getLanIpPool());
        cusRouter.setBandwidthMbps(cusRouterRequestDto.getBandwidth());
        cusRouter.setOwnership(cusRouterRequestDto.getOwnership());
        cusRouter.setAsNumber(cusRouterRequestDto.getAsNumber());
        NetworkAsset networkAsset = networkAssetService.findOne(cusRouterRequestDto.getAssetId());
        cusRouter.setNetworkAsset(networkAsset);
        return cusRouter;
    }

    @SneakyThrows
    @Override
    public CusRouterResponseDto convertToDto(CusRouter cusRouter) {
        CusRouterResponseDto responseDto = new CusRouterResponseDto();
        responseDto.setId(cusRouter.getId());
        responseDto.setWanIpAddress(cusRouter.getWanIpAddress());
        responseDto.setLanIpPool(cusRouter.getLanIpPool());
        responseDto.setAsset(networkAssetService.convertToDto(cusRouter.getNetworkAsset()));
        responseDto.setBandwidth(cusRouter.getBandwidthMbps());
        responseDto.setOwnership(cusRouter.getOwnership());
        responseDto.setAsNumber(cusRouter.getAsNumber());

        return responseDto;
    }

    @Override
    public CusRouter findOne(Integer id) throws Exception {
        return cusRouterRepo.findActiveOne(id).orElseThrow(() -> new NotFoundException(CUSTOMER_ROUTER));
    }
}
