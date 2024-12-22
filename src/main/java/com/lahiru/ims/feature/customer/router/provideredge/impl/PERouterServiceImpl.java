package com.lahiru.ims.feature.customer.router.provideredge.impl;

import com.lahiru.ims.common.dto.PaginationResponse;
import com.lahiru.ims.exception.NotFoundException;
import com.lahiru.ims.feature.customer.router.provideredge.PERouter;
import com.lahiru.ims.feature.customer.router.provideredge.PERouterRepo;
import com.lahiru.ims.feature.customer.router.provideredge.PERouterService;
import com.lahiru.ims.feature.customer.router.provideredge.dto.PERouterRequestDto;
import com.lahiru.ims.feature.customer.router.provideredge.dto.PERouterResponseDto;
import com.lahiru.ims.feature.inventory.asset.network.Network;
import com.lahiru.ims.feature.inventory.asset.network.NetworkService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@Transactional
@RequiredArgsConstructor
public class PERouterServiceImpl implements PERouterService {
    public static final String PE_ROUTER = "Provider Edge Router";
    private final Logger LOGGER = LoggerFactory.getLogger(PERouterServiceImpl.class);
    private final ModelMapper modelMapper;
    private final PERouterRepo peRouterRepo;
    private final NetworkService networkService;

    @Override
    public PaginationResponse<PERouterResponseDto> findByPageWise(int page, int pageSize) throws Exception {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<PERouter> allByPageWise = peRouterRepo.findAllByPageWise(pageable);
        int totalCount = (int) allByPageWise.getTotalElements();
        List<PERouterResponseDto> list = allByPageWise.map(this::convertToDto).stream().toList();
        return new PaginationResponse<>(list, totalCount);
    }

    @Override
    public List<PERouterResponseDto> findAll() throws Exception {
        return peRouterRepo.findAllActive().stream().map(this::convertToDto).toList();
    }

    @Override
    public PERouterResponseDto createOne(PERouterRequestDto peRouterRequestDto) throws Exception {
        PERouter peRouter = convertToModel(peRouterRequestDto);
        PERouter savedPERouter = peRouterRepo.save(peRouter);
        return convertToDto(savedPERouter);
    }

    @Override
    public PERouterResponseDto updateOne(int id, PERouterRequestDto peRouterRequestDto) throws Exception {
        if (!peRouterRepo.isActiveById(id)) throw new NotFoundException(PE_ROUTER);
        PERouter peRouter = convertToModel(peRouterRequestDto);
        peRouter.setId(id);
        PERouter updatedRouter = peRouterRepo.save(peRouter);
        return convertToDto(updatedRouter);
    }

    @Override
    public PERouterResponseDto deleteOne(int id) throws Exception {
        PERouter peRouter = peRouterRepo.findActiveOne(id).orElseThrow(() -> new NotFoundException(PE_ROUTER));
        peRouter.setIsActive(false);
        PERouter deletedRouter = peRouterRepo.save(peRouter);
        return convertToDto(deletedRouter);
    }

    @Override
    public PERouter convertToModel(PERouterRequestDto peRouterRequestDto) {
        modelMapper.typeMap(PERouterRequestDto.class, PERouter.class)
                .addMappings(mapper -> {
                    mapper.<Integer>map(PERouterRequestDto::getAssetId, (dest, value) -> {
                        try {
                            dest.setNetworkAsset(networkService.findOne(value));
                        } catch (Exception e) {
                            throwMapperError(e);
                        }
                    });
                    mapper.<Integer>map(PERouterRequestDto::getNetworkSwitchId, (dest, value) -> {
                        try {
                            dest.setNetworkSwitch(networkService.findOne(value));
                        } catch (Exception e) {
                            throwMapperError(e);
                        }
                    });
                });
        PERouter peRouter = modelMapper.map(peRouterRequestDto, PERouter.class);
        peRouter.setIsActive(true);
        return peRouter;
    }

    @Override
    public PERouterResponseDto convertToDto(PERouter peRouter) {
        modelMapper.typeMap(PERouter.class, PERouterResponseDto.class)
                .addMappings(mapper -> {
                    mapper.<Network>map(PERouter::getNetworkAsset, (dest, value) -> {
                        try {
                            dest.setAsset(networkService.convertToDto(value));
                        } catch (Exception e) {
                            throwMapperError(e);
                        }
                    });
                    mapper.<Network>map(PERouter::getNetworkSwitch, (dest, value) -> {
                        try {
                            dest.setNetworkSwitch(networkService.convertToDto(value));
                        } catch (Exception e) {
                            throwMapperError(e);
                        }
                    });
                });
        return modelMapper.map(peRouter, PERouterResponseDto.class);
    }

    private void throwMapperError(Exception e) {
        if (Objects.equals(e.getClass(), NotFoundException.class)) throw new NotFoundException(e.getMessage(), true);
        else throw new RuntimeException(e.getMessage());
    }
}
