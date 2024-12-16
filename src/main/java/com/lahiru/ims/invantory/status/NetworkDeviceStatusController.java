package com.lahiru.ims.invantory.status;

import com.lahiru.ims.common.dto.StandardReponse;
import com.lahiru.ims.utils.ResponseEntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("${endpoints.asset-network-device-status}")
@RequiredArgsConstructor
public class NetworkDeviceStatusController {

    private final NetworkDeviceStatusService service;

    @GetMapping("all")
    public ResponseEntity<StandardReponse<List<NetworkDeviceStatusResponseDto>>> getAll() throws Exception {
        List<NetworkDeviceStatusResponseDto> all = service.findAll();
        return ResponseEntityManager.ok(all);
    }
}
