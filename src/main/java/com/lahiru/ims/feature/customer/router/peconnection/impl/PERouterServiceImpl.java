package com.lahiru.ims.feature.customer.router.peconnection.impl;

import com.lahiru.ims.common.dto.PaginationResponse;
import com.lahiru.ims.exception.MapperException;
import com.lahiru.ims.exception.NotFoundException;
import com.lahiru.ims.feature.customer.router.pe.PERouter;
import com.lahiru.ims.feature.customer.router.pe.PERouterService;
import com.lahiru.ims.feature.customer.router.peconnection.PERouterConnection;
import com.lahiru.ims.feature.customer.router.peconnection.PERouterConnectionRepo;
import com.lahiru.ims.feature.customer.router.peconnection.PERouterConnectionService;
import com.lahiru.ims.feature.customer.router.peconnection.dto.PERouterConnectionRequestDto;
import com.lahiru.ims.feature.customer.router.peconnection.dto.PERouterConnectionResponseDto;
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
public class PERouterServiceImpl implements PERouterConnectionService {
    public static final String PE_ROUTER_CONNECTION = "PE Router Connection";
    private final ModelMapper modelMapper;
    private final PERouterConnectionRepo peRouterConnectionRepo;
    private final NetworkService networkService;
    private final PERouterService peRouterService;

    @Override
    public PaginationResponse<PERouterConnectionResponseDto> findByPageWise(int page, int pageSize) throws Exception {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<PERouterConnection> allByPageWise = peRouterConnectionRepo.findAllByPageWise(pageable);
        int totalCount = (int) allByPageWise.getTotalElements();
        List<PERouterConnectionResponseDto> list = allByPageWise.map(this::convertToDto).stream().toList();
        return new PaginationResponse<>(list, totalCount);
    }

    @Override
    public List<PERouterConnectionResponseDto> findAll() throws Exception {
        return peRouterConnectionRepo.findAllActive().stream().map(this::convertToDto).toList();
    }

    @Override
    public PERouterConnectionResponseDto createOne(PERouterConnectionRequestDto peRouterRequestDto) throws Exception {
        PERouterConnection peRouterConnection = convertToModel(peRouterRequestDto);
        PERouterConnection savedPERouterConnection = peRouterConnectionRepo.save(peRouterConnection);
        return convertToDto(savedPERouterConnection);
    }

    @Override
    public PERouterConnectionResponseDto updateOne(int id, PERouterConnectionRequestDto peRouterRequestDto) throws Exception {
        if (!peRouterConnectionRepo.isActiveById(id)) throw new NotFoundException(PE_ROUTER_CONNECTION);
        PERouterConnection peRouterConnection = convertToModel(peRouterRequestDto);
        peRouterConnection.setId(id);
        PERouterConnection updatedRouter = peRouterConnectionRepo.save(peRouterConnection);
        return convertToDto(updatedRouter);
    }

    @Override
    public PERouterConnectionResponseDto deleteOne(int id) throws Exception {
        PERouterConnection peRouterConnection = peRouterConnectionRepo.findActiveOne(id).orElseThrow(() -> new NotFoundException(PE_ROUTER_CONNECTION));
        peRouterConnection.setIsActive(false);
        PERouterConnection deletedRouter = peRouterConnectionRepo.save(peRouterConnection);
        return convertToDto(deletedRouter);
    }

    @Override
    public PERouterConnection convertToModel(PERouterConnectionRequestDto peRouterRequestDto) {
        modelMapper.typeMap(PERouterConnectionRequestDto.class, PERouterConnection.class)
                .addMappings(mapper -> {
                    mapper.<Integer>map(PERouterConnectionRequestDto::getPeRouterId, (dest, value) -> {
                        try {
                            dest.setPeRouter(peRouterService.findOne(value));
                        } catch (Exception e) {
                            throw new MapperException(e);
                        }
                    });
                    mapper.<Integer>map(PERouterConnectionRequestDto::getNetworkSwitchId, (dest, value) -> {
                        try {
                            dest.setNetworkSwitch(networkService.findOne(value));
                        } catch (Exception e) {
                            throw new MapperException(e);
                        }
                    });
                });
        PERouterConnection peRouterConnection = modelMapper.map(peRouterRequestDto, PERouterConnection.class);
        peRouterConnection.setIsActive(true);
        return peRouterConnection;
    }

    @Override
    public PERouterConnectionResponseDto convertToDto(PERouterConnection peRouterConnection) {
        modelMapper.typeMap(PERouterConnection.class, PERouterConnectionResponseDto.class)
                .addMappings(mapper -> {
                    mapper.<PERouter>map(PERouterConnection::getPeRouter, (dest, value) -> {
                        try {
                            dest.setPeRouter(peRouterService.convertToDto(value));
                        } catch (Exception e) {
                            throw new MapperException(e);
                        }
                    });
                    mapper.<Network>map(PERouterConnection::getNetworkSwitch, (dest, value) -> {
                        try {
                            dest.setNetworkSwitch(networkService.convertToDto(value));
                        } catch (Exception e) {
                            throw new MapperException(e);
                        }
                    });
                });
        return modelMapper.map(peRouterConnection, PERouterConnectionResponseDto.class);
    }

    @Override
    public PERouterConnection findOne(Integer id) throws Exception {
        return peRouterConnectionRepo.findActiveOne(id).orElseThrow(() -> new NotFoundException(PE_ROUTER_CONNECTION));
    }
}
