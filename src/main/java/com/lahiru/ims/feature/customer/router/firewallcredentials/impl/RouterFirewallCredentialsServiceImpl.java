package com.lahiru.ims.feature.customer.router.firewallcredentials.impl;

import com.lahiru.ims.exception.NotFoundException;
import com.lahiru.ims.feature.customer.router.firewallcredentials.RouterFirewallCredentialController;
import com.lahiru.ims.feature.customer.router.firewallcredentials.RouterFirewallCredentials;
import com.lahiru.ims.feature.customer.router.firewallcredentials.RouterFirewallCredentialsRepo;
import com.lahiru.ims.feature.customer.router.firewallcredentials.RouterFirewallCredentialsService;
import com.lahiru.ims.feature.customer.router.firewallcredentials.dto.RouterFirewallCredentialsRequestDto;
import com.lahiru.ims.feature.customer.router.firewallcredentials.dto.RouterFirewallCredentialsResponseDto;
import com.lahiru.ims.feature.customer.service.connection.Connection;
import com.lahiru.ims.feature.customer.service.connection.ConnectionRepo;
import com.lahiru.ims.feature.customer.service.connection.ConnectionService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class RouterFirewallCredentialsServiceImpl implements RouterFirewallCredentialsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RouterFirewallCredentialsServiceImpl.class);
    public static final String FIREWALL_CREDENTIALS = "Firewall Credentials";
    private final RouterFirewallCredentialsRepo firewallCredentialsRepo;
    private final ModelMapper modelMapper;
   private final ConnectionRepo connectionRepo;

    @Override
    public RouterFirewallCredentials createOne(RouterFirewallCredentialsRequestDto firewallCredentialsDto) throws Exception {
        RouterFirewallCredentials routerFirewallCredentials =
                new RouterFirewallCredentials(firewallCredentialsDto.getUsername(), firewallCredentialsDto.getPassword());
        return firewallCredentialsRepo.save(routerFirewallCredentials);
    }

    @Override
    public RouterFirewallCredentials updateOne(Integer id, RouterFirewallCredentialsRequestDto firewallCredentialsDto) throws Exception {

        RouterFirewallCredentials existing = firewallCredentialsRepo.findActiveOne(id)
                .orElseThrow(() -> new NotFoundException(FIREWALL_CREDENTIALS));
        existing.setIsActive(false);
       firewallCredentialsRepo.save(existing);

       LOGGER.info("Firewall credentials {}", firewallCredentialsDto);

        RouterFirewallCredentials newCredentials = new RouterFirewallCredentials();
        newCredentials.setUsername(firewallCredentialsDto.getUsername());
        newCredentials.setPassword(firewallCredentialsDto.getPassword());
        newCredentials.setIsActive(true);

        RouterFirewallCredentials saved = firewallCredentialsRepo.save(newCredentials);

        Connection connection = connectionRepo.findByRouterFirewallCredentials(existing).orElseThrow(() -> new NotFoundException("Connection Not Found for Firewall Credentials", true));
        connection.setRouterFirewallCredentials(saved);
        connectionRepo.save(connection);

        return saved;
    }

    @Override
    public RouterFirewallCredentials convertToModel(RouterFirewallCredentialsRequestDto firewallCredentialsDto) throws Exception {
        return modelMapper.map(firewallCredentialsDto, RouterFirewallCredentials.class);
    }

    @Override
    public RouterFirewallCredentialsResponseDto convertToDto(RouterFirewallCredentials routerFirewallCredentials) throws Exception {
        return modelMapper.map(routerFirewallCredentials, RouterFirewallCredentialsResponseDto.class);
    }
}
