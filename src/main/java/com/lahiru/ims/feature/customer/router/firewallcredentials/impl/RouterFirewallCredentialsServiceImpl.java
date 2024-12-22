package com.lahiru.ims.feature.customer.router.firewallcredentials.impl;

import com.lahiru.ims.exception.NotFoundException;
import com.lahiru.ims.feature.customer.router.firewallcredentials.RouterFirewallCredentials;
import com.lahiru.ims.feature.customer.router.firewallcredentials.RouterFirewallCredentialsRepo;
import com.lahiru.ims.feature.customer.router.firewallcredentials.RouterFirewallCredentialsService;
import com.lahiru.ims.feature.customer.router.firewallcredentials.dto.RouterFirewallCredentialsDto;
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

    @Override
    public RouterFirewallCredentialsDto createOne(RouterFirewallCredentialsDto firewallCredentialsDto) throws Exception {
        RouterFirewallCredentials routerFirewallCredentials =
                new RouterFirewallCredentials(firewallCredentialsDto.getUsername(), firewallCredentialsDto.getPassword());
        RouterFirewallCredentials saved = firewallCredentialsRepo.save(routerFirewallCredentials);
        return modelMapper.map(saved, RouterFirewallCredentialsDto.class);
    }

    @Override
    public RouterFirewallCredentialsDto updateOne(Integer id, RouterFirewallCredentialsDto firewallCredentialsDto) throws Exception {
        try {
            RouterFirewallCredentials credentials = firewallCredentialsRepo.findActiveOne(id)
                    .orElseThrow(() -> new NotFoundException(FIREWALL_CREDENTIALS));
            credentials.setIsActive(false);
        } catch (NotFoundException e) {
            LOGGER.warn(e.getMessage());
        }

        RouterFirewallCredentials routerFirewallCredentials = new RouterFirewallCredentials(firewallCredentialsDto.getUsername(), firewallCredentialsDto.getPassword());
        RouterFirewallCredentials updated = firewallCredentialsRepo.save(routerFirewallCredentials);
        return modelMapper.map(updated, RouterFirewallCredentialsDto.class);
    }

    @Override
    public RouterFirewallCredentialsDto getOne(Integer id) throws Exception {
        RouterFirewallCredentials routerFirewallCredentials = firewallCredentialsRepo.findActiveOne(id)
                .orElseThrow(() -> new NotFoundException(FIREWALL_CREDENTIALS));
        return modelMapper.map(routerFirewallCredentials, RouterFirewallCredentialsDto.class);
    }
}
