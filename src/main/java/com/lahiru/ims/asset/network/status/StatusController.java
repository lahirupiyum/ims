package com.lahiru.ims.asset.network.status;

import com.lahiru.ims.common.ResponseEntityManager;
import com.lahiru.ims.common.dto.StandardReponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("${endpoints.asset-network-device-status}")
@RequiredArgsConstructor
public class StatusController {

    private final StatusService service;

    @GetMapping("all")
    public ResponseEntity<StandardReponse<List<StatusResponseDto>>> getAll() throws Exception {
        List<StatusResponseDto> all = service.findAll();
        return ResponseEntityManager.ok(all);
    }
}
