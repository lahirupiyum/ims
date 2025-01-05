package com.lahiru.ims.feature.customer.router.peconnection.impl;

import com.lahiru.ims.common.dto.PaginationResponse;
import com.lahiru.ims.exception.MapperException;
import com.lahiru.ims.exception.NotFoundException;
import com.lahiru.ims.feature.customer.router.peconnection.PERouterConnection;
import com.lahiru.ims.feature.customer.router.peconnection.PERouterConnectionRepo;
import com.lahiru.ims.feature.customer.router.peconnection.PERouterConnectionService;
import com.lahiru.ims.feature.customer.router.peconnection.dto.PERouterConnectionRequestDto;
import com.lahiru.ims.feature.customer.router.peconnection.dto.PERouterConnectionResponseDto;
import com.lahiru.ims.feature.inventory.asset.network.Network;
import com.lahiru.ims.feature.inventory.asset.network.NetworkService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger log = LoggerFactory.getLogger(PERouterServiceImpl.class);
    private final ModelMapper modelMapper;
    private final PERouterConnectionRepo peRouterConnectionRepo;
    private final NetworkService networkService;

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

    @SneakyThrows
    @Override
    public PERouterConnection convertToModel(PERouterConnectionRequestDto peRouterRequestDto) {
        PERouterConnection connection = new PERouterConnection();
        connection.setIp(peRouterRequestDto.getIp());
        connection.setPort(peRouterRequestDto.getPort());
        connection.setSwitchPort(peRouterRequestDto.getSwitchPort());
        connection.setWanIpPool(peRouterRequestDto.getWanIpPool());
        connection.setPeRouter(networkService.findOne(peRouterRequestDto.getPeRouterId()));
        connection.setNetworkSwitch(networkService.findOne(peRouterRequestDto.getNetworkSwitchId()));
        connection.setIsActive(true);
        return connection;
    }

    @SneakyThrows
    @Override
    public PERouterConnectionResponseDto convertToDto(PERouterConnection peRouterConnection) {
        PERouterConnectionResponseDto responseDto = new PERouterConnectionResponseDto();

        responseDto.setId(peRouterConnection.getId());
        responseDto.setPort(peRouterConnection.getPort());
        responseDto.setIp(peRouterConnection.getIp());
        responseDto.setSwitchPort(peRouterConnection.getSwitchPort());
        responseDto.setWanIpPool(peRouterConnection.getWanIpPool());
        responseDto.setPeRouter(networkService.convertToDto(peRouterConnection.getPeRouter()));
        responseDto.setNetworkSwitch(networkService.convertToDto(peRouterConnection.getNetworkSwitch()));

        return responseDto;
    }

    @Override
    public PERouterConnection findOne(Integer id) throws Exception {
        return peRouterConnectionRepo.findActiveOne(id).orElseThrow(() -> new NotFoundException(PE_ROUTER_CONNECTION));
    }
}
