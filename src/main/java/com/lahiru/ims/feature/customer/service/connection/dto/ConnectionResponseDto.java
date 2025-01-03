package com.lahiru.ims.feature.customer.service.connection.dto;

import com.lahiru.ims.feature.customer.customer.dto.CustomerResponseDto;
import com.lahiru.ims.feature.customer.lastmile.connection.dto.LastMileConnectionResponseDto;
import com.lahiru.ims.feature.customer.router.customer.dto.CusRouterResponseDto;
import com.lahiru.ims.feature.customer.router.peconnection.dto.PERouterConnectionResponseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class ConnectionResponseDto extends ConnectionDto {
    private LastMileConnectionResponseDto lastMileConnection;
    private CustomerResponseDto customer;
    private PERouterConnectionResponseDto peRouter;
    private CusRouterResponseDto cusRouter;
    private Boolean activeStatus;
}