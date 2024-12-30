package com.lahiru.ims.feature.customer.service.connection.impl;

import com.lahiru.ims.common.dto.PaginationResponse;
import com.lahiru.ims.common.model.StatusAwareAudit;
import com.lahiru.ims.common.service.EntityFinderService;
import com.lahiru.ims.common.service.ModelMapperService;
import com.lahiru.ims.exception.MapperException;
import com.lahiru.ims.exception.NotFoundException;
import com.lahiru.ims.feature.customer.customer.Customer;
import com.lahiru.ims.feature.customer.customer.CustomerService;
import com.lahiru.ims.feature.customer.lastmile.connection.LastMileConnection;
import com.lahiru.ims.feature.customer.lastmile.connection.LastMileConnectionService;
import com.lahiru.ims.feature.customer.lastmile.connection.dto.LastMileConnectionRequestDto;
import com.lahiru.ims.feature.customer.router.customer.CusRouter;
import com.lahiru.ims.feature.customer.router.customer.CusRouterService;
import com.lahiru.ims.feature.customer.router.firewallcredentials.RouterFirewallCredentials;
import com.lahiru.ims.feature.customer.router.firewallcredentials.RouterFirewallCredentialsService;
import com.lahiru.ims.feature.customer.router.firewallcredentials.dto.RouterFirewallCredentialsDto;
import com.lahiru.ims.feature.customer.router.provideredge.PERouter;
import com.lahiru.ims.feature.customer.router.provideredge.PERouterService;
import com.lahiru.ims.feature.customer.service.connection.Connection;
import com.lahiru.ims.feature.customer.service.connection.ConnectionRepo;
import com.lahiru.ims.feature.customer.service.connection.ConnectionService;
import com.lahiru.ims.feature.customer.service.connection.dto.ConnectionRequestDto;
import com.lahiru.ims.feature.customer.service.connection.dto.ConnectionResponseDto;
import com.lahiru.ims.feature.customer.service.enums.NetworkServiceType;
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
public class ConnectionServiceImpl implements ConnectionService {
    private final ConnectionRepo connectionRepo;
    private final LastMileConnectionService lastMileConnectionService;
    private final RouterFirewallCredentialsService firewallCredentialsService;
    private final ModelMapper modelMapper;
    private final CustomerService customerService;
    private final CusRouterService cusRouterService;
    private final PERouterService peRouterService;

    @Override
    public PaginationResponse<ConnectionResponseDto> findIllByPageWise(int page, int pageSize) throws Exception {
        return getPageWiseByNetworkServiceType(page, pageSize, NetworkServiceType.ILL);
    }

    @Override
    public PaginationResponse<ConnectionResponseDto> findMplsByPageWise(int page, int pageSize) throws Exception {
        return getPageWiseByNetworkServiceType(page, pageSize, NetworkServiceType.MPLS);
    }

    private PaginationResponse<ConnectionResponseDto> getPageWiseByNetworkServiceType(int page, int pageSize, NetworkServiceType serviceType) throws Exception {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Connection> byServiceType = connectionRepo.findByServiceType(serviceType, pageable);
        List<ConnectionResponseDto> all = byServiceType.map(this::convertToDto).stream().toList();
        int totalCount = (int) byServiceType.getTotalElements();
        return new PaginationResponse<>(all, totalCount);
    }

    @Override
    public PaginationResponse<ConnectionResponseDto> findByPageWise(int page, int pageSize) throws Exception {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Connection> allByPageWise = connectionRepo.findAll(pageable);
        List<ConnectionResponseDto> responseDtoList = allByPageWise.map(this::convertToDto).stream().toList();
        int totalCount = (int) allByPageWise.getTotalElements();
        return new PaginationResponse<>(responseDtoList, totalCount);
    }

    @Override
    public List<ConnectionResponseDto> findAll() throws Exception {
        return connectionRepo.findAll().stream().map(this::convertToDto).toList();
    }

    @Override
    public ConnectionResponseDto createOne(ConnectionRequestDto connectionRequestDto) throws Exception {
        LastMileConnectionRequestDto lastMileConnectionDto = connectionRequestDto.getLastMileConnection();
        RouterFirewallCredentialsDto firewallCredentials = connectionRequestDto.getFirewallCredentials();

        LastMileConnection createdLastMileConnection = lastMileConnectionService.createOne(lastMileConnectionDto);
        RouterFirewallCredentials routerFirewallCredentials = (firewallCredentials == null) ? null : firewallCredentialsService.createOne(firewallCredentials);

        Connection connection = convertToModel(connectionRequestDto);
        connection.setLastMileConnection(createdLastMileConnection);
        connection.setRouterFirewallCredentials(routerFirewallCredentials);

        connectionRepo.save(connection);
        return convertToDto(connection);
    }

    @Override
    public ConnectionResponseDto updateOne(int id, ConnectionRequestDto connectionRequestDto) throws Exception {
        Connection fetchedConnection = connectionRepo.findById(id).orElseThrow(() -> new NotFoundException("Service Connection"));
        Integer lastMileConnectionId = fetchedConnection.getLastMileConnection().getId();
        Integer firewallCredentialsID = fetchedConnection.getRouterFirewallCredentials().getId();

        LastMileConnection lastMileConnection = lastMileConnectionService.updateOne(lastMileConnectionId, connectionRequestDto.getLastMileConnection());

        RouterFirewallCredentialsDto firewallCredentials = connectionRequestDto.getFirewallCredentials();
        RouterFirewallCredentials routerFirewallCredentials = (firewallCredentials == null) ? null : firewallCredentialsService.updateOne(firewallCredentialsID, firewallCredentials);

        Connection connection = convertToModel(connectionRequestDto);
        connection.setLastMileConnection(lastMileConnection);
        connection.setRouterFirewallCredentials(routerFirewallCredentials);

        connectionRepo.save(connection);
        return convertToDto(connection);
    }

    @Override
    public ConnectionResponseDto deleteOne(int id) throws Exception {
        Connection connection = connectionRepo.findById(id).orElseThrow(() -> new NotFoundException("Service Connection"));
        connection.setActiveStatus(false);
        connectionRepo.save(connection);
        return convertToDto(connection);
    }

    @Override
    public Connection convertToModel(ConnectionRequestDto connectionRequestDto) {
        modelMapper.typeMap(ConnectionRequestDto.class, Connection.class)
                .addMappings(mapper -> {
                    mapper.skip(Connection::setRouterFirewallCredentials);
                    mapper.skip(Connection::setLastMileConnection);
                    mapper.<Integer>map(ConnectionRequestDto::getCustomerId, (dest, value) -> dest.setCustomer(getModel(value, customerService)));
                    mapper.<Integer>map(ConnectionRequestDto::getCusRouterId, (dest, value) ->
                            dest.setCusRouter(getModel(value, cusRouterService))
                    );
                    mapper.<Integer>map(ConnectionRequestDto::getPeRouterId, (dest, value) ->
                            dest.setPeRouter(getModel(value, peRouterService))
                    );
                });

        Connection connection = modelMapper.map(connectionRequestDto, Connection.class);
        connection.setActiveStatus(true);
        return connection;
    }

    @Override
    public ConnectionResponseDto convertToDto(Connection connection) {
        modelMapper.typeMap(Connection.class, ConnectionResponseDto.class)
                .addMappings(mapper -> {
                    mapper.<LastMileConnection>map(Connection::getLastMileConnection, (dest, value) -> {
                        dest.setLastMileConnection(getResponseDto(value, lastMileConnectionService));
                    });
                    mapper.<RouterFirewallCredentials>map(Connection::getRouterFirewallCredentials, (dest, value) -> {
                        dest.setFirewallCredentials(getResponseDto(value, firewallCredentialsService));
                    });
                    mapper.<Customer>map(Connection::getCustomer, (dest, value) -> {
                        dest.setCustomer(getResponseDto(value, customerService));
                    });
                    mapper.<CusRouter>map(Connection::getCusRouter, (dest, value) -> {
                        dest.setCusRouter(getResponseDto(value, cusRouterService));
                    });
                    mapper.<PERouter>map(Connection::getPeRouter, (dest, value) -> {
                        dest.setPeRouter(getResponseDto(value, peRouterService));
                    });
                });


        return modelMapper.map(connection, ConnectionResponseDto.class);
    }

    private <Model extends StatusAwareAudit, ModelService extends EntityFinderService<Model>> Model getModel(Integer id, ModelService modelService) {
        try {
            return modelService.findOne(id);
        } catch (Exception e) {
            throw new MapperException(e);
        }
    }

    private <Model, RequestDto, ResponseDto, ModelService extends ModelMapperService<Model, RequestDto, ResponseDto>> ResponseDto getResponseDto(Model model, ModelService modelService) {
        try {
            return modelService.convertToDto(model);
        } catch (Exception e) {
            throw new MapperException(e);
        }
    }
}
