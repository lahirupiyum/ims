package com.lahiru.ims.feature.customer.service.connection.dto;

import com.lahiru.ims.feature.customer.router.firewallcredentials.dto.RouterFirewallCredentialsRequestDto;
import com.lahiru.ims.feature.customer.service.enums.ManageStatus;
import com.lahiru.ims.feature.customer.service.enums.NetworkServiceType;
import com.lahiru.ims.feature.customer.service.enums.ProvisioningStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class ConnectionDto {
    private Date dsp;
    private Date serviceChange;
    private Date terminationDate;
    private ProvisioningStatus provisioningStatus;
    private String remarks;
    private NetworkServiceType networkServiceType;
    private ManageStatus manageStatus;
}
