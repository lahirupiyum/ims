package com.lahiru.ims.feature.customer.router.firewallcredentials;

import com.lahiru.ims.common.dto.StandardReponse;
import com.lahiru.ims.feature.customer.router.firewallcredentials.dto.RouterFirewallCredentialsRequestDto;
import com.lahiru.ims.feature.customer.router.firewallcredentials.dto.RouterFirewallCredentialsResponseDto;
import com.lahiru.ims.utils.ResponseEntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${endpoints.router-firewall-credentials}")
@RequiredArgsConstructor
public class RouterFirewallCredentialController {
    private final RouterFirewallCredentialsService routerFirewallCredentialsService;

    @PutMapping("/{id}")
    ResponseEntity<StandardReponse<RouterFirewallCredentialsResponseDto>> updateOne(@PathVariable("id") Integer id, @RequestBody RouterFirewallCredentialsRequestDto requestDto) throws Exception {
        RouterFirewallCredentials routerFirewallCredentials = routerFirewallCredentialsService.updateOne(id, requestDto);
        RouterFirewallCredentialsResponseDto responseDto = routerFirewallCredentialsService.convertToDto(routerFirewallCredentials);
        return ResponseEntityManager.updated(responseDto, "Router Firewall Credentials");
    }
}
