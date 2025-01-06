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
import com.lahiru.ims.feature.customer.router.peconnection.PERouterConnection;
import com.lahiru.ims.feature.customer.router.peconnection.PERouterConnectionService;
import com.lahiru.ims.feature.customer.service.connection.Connection;
import com.lahiru.ims.feature.customer.service.connection.ConnectionRepo;
import com.lahiru.ims.feature.customer.service.connection.ConnectionService;
import com.lahiru.ims.feature.customer.service.connection.dto.ConnectionRequestDto;
import com.lahiru.ims.feature.customer.service.connection.dto.ConnectionResponseDto;
import com.lahiru.ims.feature.customer.service.enums.NetworkServiceType;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
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
    private final PERouterConnectionService peRouterConnectionService;

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

    @SneakyThrows
    @Override
    public Connection convertToModel(ConnectionRequestDto connectionRequestDto) {
        Connection connection = new Connection();
        connection.setRemarks(connectionRequestDto.getRemarks());
        connection.setCustomer(customerService.findOne(connectionRequestDto.getCustomerId()));
        connection.setPeRouterConnection(peRouterConnectionService.findOne(connectionRequestDto.getPeRouterId()));
        connection.setCusRouter(cusRouterService.findOne(connectionRequestDto.getCusRouterId()));
        connection.setActiveStatus(true);
        connection.setManageStatus(connectionRequestDto.getManageStatus());
        connection.setDsp(connectionRequestDto.getDsp());
        connection.setServiceChange(connectionRequestDto.getServiceChange());
        connection.setTerminationDate(connectionRequestDto.getTerminationDate());
        connection.setServiceType(connectionRequestDto.getNetworkServiceType());

        return connection;
    }

    @Override
    public ConnectionResponseDto convertToDto(Connection connection) {
        ConnectionResponseDto responseDto = new ConnectionResponseDto();
        responseDto.setFirewallCredentials(getResponseDto(connection.getRouterFirewallCredentials(), firewallCredentialsService));
        responseDto.setLastMileConnection(getResponseDto(connection.getLastMileConnection(), lastMileConnectionService));
        responseDto.setCustomer(getResponseDto(connection.getCustomer(), customerService));
        responseDto.setCusRouter(getResponseDto(connection.getCusRouter(), cusRouterService));
        responseDto.setPeRouter(getResponseDto(connection.getPeRouterConnection(), peRouterConnectionService));
        responseDto.setDsp(connection.getDsp());
        responseDto.setServiceChange(connection.getServiceChange());
        responseDto.setTerminationDate(connection.getTerminationDate());
        responseDto.setActiveStatus(connection.getActiveStatus());
        responseDto.setRemarks(connection.getRemarks());
        responseDto.setNetworkServiceType(connection.getServiceType());
        responseDto.setManageStatus(connection.getManageStatus());
        responseDto.setId(connection.getId());

        return responseDto;
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
