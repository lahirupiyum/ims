package com.lahiru.ims.feature.customer.router.customer.impl;

import com.lahiru.ims.common.dto.PaginationResponse;
import com.lahiru.ims.exception.MapperException;
import com.lahiru.ims.exception.NotFoundException;
import com.lahiru.ims.feature.customer.router.customer.CusRouter;
import com.lahiru.ims.feature.customer.router.customer.CusRouterRepo;
import com.lahiru.ims.feature.customer.router.customer.CusRouterService;
import com.lahiru.ims.feature.customer.router.customer.dto.CusRouterRequestDto;
import com.lahiru.ims.feature.customer.router.customer.dto.CusRouterResponseDto;
import com.lahiru.ims.feature.inventory.asset.network.Network;
import com.lahiru.ims.feature.inventory.asset.network.NetworkService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CusRouterServiceImpl implements CusRouterService {
    public static final String CUSTOMER_ROUTER = "Customer Router";
    private final CusRouterRepo cusRouterRepo;
    private final ModelMapper modelMapper;
    private final NetworkService networkService;

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
        CusRouter savedRouter = cusRouterRepo.save(cusRouter);
        return convertToDto(savedRouter);
    }

    @Override
    public CusRouterResponseDto updateOne(int id, CusRouterRequestDto cusRouterRequestDto) throws Exception {
        if (!cusRouterRepo.isActiveById(id)) throw new NotFoundException(CUSTOMER_ROUTER);

        CusRouter cusRouter = convertToModel(cusRouterRequestDto);
        cusRouter.setId(id);
        CusRouter updatedRouter = cusRouterRepo.save(cusRouter);
        return convertToDto(updatedRouter);
    }

    @Override
    public CusRouterResponseDto deleteOne(int id) throws Exception {
        CusRouter cusRouter = cusRouterRepo.findActiveOne(id).orElseThrow(() -> new NotFoundException(CUSTOMER_ROUTER));
        cusRouter.setIsActive(false);
        CusRouter deletedRouter = cusRouterRepo.save(cusRouter);
        return convertToDto(deletedRouter);
    }

    @Override
    public CusRouter convertToModel(CusRouterRequestDto cusRouterRequestDto) {
        modelMapper.typeMap(CusRouterRequestDto.class, CusRouter.class)
                .addMappings(mapper -> {
                    mapper.<Integer>map(CusRouterRequestDto::getAssetId, (dest, value) -> {
                        try {
                            dest.setNetworkAsset(networkService.findOne(value));
                        } catch (Exception e) {
                            throw new MapperException(e);
                        }
                    } );
                });
        CusRouter cusRouter = modelMapper.map(cusRouterRequestDto, CusRouter.class);
        cusRouter.setIsActive(true);
        return cusRouter;
    }

    @Override
    public CusRouterResponseDto convertToDto(CusRouter cusRouter) {
        modelMapper.typeMap(CusRouter.class, CusRouterResponseDto.class)
                .addMappings(mapper -> {
                    mapper.<Network>map(CusRouter::getNetworkAsset, (dest, value) -> {
                        try {
                            dest.setAsset(networkService.convertToDto(value));
                        } catch (Exception e) {
                            throw new MapperException(e);
                        }
                    });
                });

        return modelMapper.map(cusRouter, CusRouterResponseDto.class);
    }

    @Override
    public CusRouter findOne(Integer id) throws Exception {
        return cusRouterRepo.findActiveOne(id).orElseThrow(() -> new NotFoundException(CUSTOMER_ROUTER));
    }
}
