package com.lahiru.ims.feature.customer.router.pe.impl;

import com.lahiru.ims.common.dto.PaginationResponse;
import com.lahiru.ims.exception.MapperException;
import com.lahiru.ims.exception.NotFoundException;
import com.lahiru.ims.feature.customer.router.pe.PERouter;
import com.lahiru.ims.feature.customer.router.pe.PERouterRepo;
import com.lahiru.ims.feature.customer.router.pe.PERouterService;
import com.lahiru.ims.feature.customer.router.pe.dto.PERouterRequestDto;
import com.lahiru.ims.feature.customer.router.pe.dto.PERouterResponseDto;
import com.lahiru.ims.feature.inventory.asset.network.Network;
import com.lahiru.ims.feature.inventory.asset.network.NetworkService;
import com.lahiru.ims.feature.inventory.asset.network.dto.NetworkAssetResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PERouterServiceImpl implements PERouterService {
    private final ModelMapper modelMapper;
    private final NetworkService networkService;
    private final PERouterRepo peRouterRepo;

    @SneakyThrows
    @Override
    public PaginationResponse<PERouterResponseDto> findByPageWise(int page, int pageSize) throws Exception {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<PERouter> peRouterAll = peRouterRepo.findAll(pageable);
        int totalElements = (int) peRouterAll.getTotalElements();
        List<PERouterResponseDto> peRouterResponseDtoList = peRouterAll.map(this::convertToDto).stream().toList();
        return new PaginationResponse<>(peRouterResponseDtoList, totalElements);
    }

    @SneakyThrows
    @Override
    public List<PERouterResponseDto> findAll() throws Exception {
        List<PERouter> all = peRouterRepo.findAll();
        return all.stream().map(this::convertToDto).toList();
    }

    @Override
    public PERouterResponseDto createOne(PERouterRequestDto peRouterRequestDto) throws Exception {
        PERouter peRouter = convertToModel(peRouterRequestDto);
        PERouter savedRouter = peRouterRepo.save(peRouter);
        return convertToDto(savedRouter);
    }

    @Override
    public PERouterResponseDto updateOne(int id, PERouterRequestDto peRouterRequestDto) throws Exception {
        if (!peRouterRepo.existsById(id)) throw new NotFoundException("PE Router");
        PERouter peRouter = convertToModel(peRouterRequestDto);
        peRouter.setId(id);
        PERouter updatedPERouter = peRouterRepo.save(peRouter);
        return convertToDto(updatedPERouter);
    }

    @Override
    public PERouterResponseDto deleteOne(int id) throws Exception {
        return null;
    }

    @Override
    public PERouter convertToModel(PERouterRequestDto peRouterRequestDto) throws Exception {
        modelMapper.typeMap(PERouterRequestDto.class, PERouter.class)
                .addMappings(mapper -> {
                    mapper.<Integer>map(PERouterRequestDto::getAssetId, (dest, value) -> {
                        try {
                            Network networkAsset = networkService.findOne(value);
                            dest.setAsset(networkAsset);
                        } catch (Exception e) {
                            throw new MapperException(e);
                        }
                    });
                });

        return modelMapper.map(peRouterRequestDto, PERouter.class);
    }

    @Override
    public PERouterResponseDto convertToDto(PERouter peRouter) throws Exception {
        modelMapper.typeMap(PERouter.class, PERouterResponseDto.class)
                .addMappings(mapper -> {
                    mapper.<Network>map(PERouter::getAsset, (dest, value) -> {
                        try {
                            NetworkAssetResponseDto networkAssetResponseDto = networkService.convertToDto(value);
                            dest.setAsset(networkAssetResponseDto);
                        }
                        catch (Exception e) {
                            throw new MapperException(e);
                        }
                    });
                });

        return modelMapper.map(peRouter, PERouterResponseDto.class);
    }

    @Override
    public PERouter findOne(Integer id) throws Exception {
        return peRouterRepo.findById(id).orElseThrow(() -> new NotFoundException("PE Rooter"));
    }
}
