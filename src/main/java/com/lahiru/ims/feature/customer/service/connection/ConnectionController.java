package com.lahiru.ims.feature.customer.service.connection;

import com.lahiru.ims.common.controller.GenericController;
import com.lahiru.ims.common.dto.PaginationResponse;
import com.lahiru.ims.common.dto.StandardReponse;
import com.lahiru.ims.feature.customer.service.connection.dto.ConnectionRequestDto;
import com.lahiru.ims.feature.customer.service.connection.dto.ConnectionResponseDto;
import com.lahiru.ims.utils.ResponseEntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${endpoints.service-connection}")
@RequiredArgsConstructor
public class ConnectionController implements GenericController<ConnectionRequestDto, ConnectionResponseDto> {
    public static final String SERVICE_CONNECTION = "Connection";
    private final ConnectionService connectionService;

    @Override
    public ResponseEntity<PaginationResponse<ConnectionResponseDto>> getAllByPageWise(int page, int pageSize) throws Exception {
        PaginationResponse<ConnectionResponseDto> byPageWise = connectionService.findByPageWise(page, pageSize);
        return ResponseEntityManager.page(byPageWise);
    }

    @GetMapping("/ill")
    public ResponseEntity<PaginationResponse<ConnectionResponseDto>> getIllPageWise(@RequestParam int page,
                                                                                    @RequestParam int pageSize) throws Exception {
        PaginationResponse<ConnectionResponseDto> illByPageWise = connectionService.findIllByPageWise(page, pageSize);
        return ResponseEntityManager.page(illByPageWise);
    }

    @GetMapping("/mpls")
    public ResponseEntity<PaginationResponse<ConnectionResponseDto>> getMplsByPageWise(@RequestParam int page,
                                                                                       @RequestParam int pageSize) throws Exception {
        PaginationResponse<ConnectionResponseDto> mplsByPageWise = connectionService.findMplsByPageWise(page, pageSize);
        return ResponseEntityManager.page(mplsByPageWise);
    }

    @Override
    public ResponseEntity<StandardReponse<List<ConnectionResponseDto>>> getAll() throws Exception {
        List<ConnectionResponseDto> all = connectionService.findAll();
        return ResponseEntityManager.ok(all);
    }

    @Override
    public ResponseEntity<StandardReponse<ConnectionResponseDto>> createOne(ConnectionRequestDto connectionRequestDto) throws Exception {
        ConnectionResponseDto one = connectionService.createOne(connectionRequestDto);
        return ResponseEntityManager.created(one, SERVICE_CONNECTION);
    }

    @Override
    public ResponseEntity<StandardReponse<ConnectionResponseDto>> updateOne(int id, ConnectionRequestDto connectionRequestDto) throws Exception {
        ConnectionResponseDto connectionResponseDto = connectionService.updateOne(id, connectionRequestDto);
        return ResponseEntityManager.updated(connectionResponseDto, SERVICE_CONNECTION);
    }

    @Override
    public ResponseEntity<StandardReponse<ConnectionResponseDto>> deleteOne(int id) throws Exception {
        ConnectionResponseDto connectionResponseDto = connectionService.deleteOne(id);
        return ResponseEntityManager.ok(connectionResponseDto, SERVICE_CONNECTION + " has been terminated!");
    }

    @PutMapping("/activate/{id}")
    public ResponseEntity<StandardReponse<ConnectionResponseDto>> activate(@PathVariable("id") Integer id) throws Exception {
        ConnectionResponseDto connectionResponseDto = connectionService.activateConnection(id);
        return ResponseEntityManager.ok(connectionResponseDto, SERVICE_CONNECTION + " has been activated!");
    }

    @GetMapping("/ill/search")
    public ResponseEntity<StandardReponse<List<ConnectionResponseDto>>> searchIllConnections(@RequestParam("key") String key) throws Exception {
        List<ConnectionResponseDto> responseDtoList = connectionService.searchIll(key);
        return ResponseEntityManager.ok(responseDtoList);
    }

    @GetMapping("/mpls/search")
    public ResponseEntity<StandardReponse<List<ConnectionResponseDto>>> searchMplsConnections(@RequestParam("key") String key) throws Exception {
        List<ConnectionResponseDto> responseDtos = connectionService.searchMpls(key);
        return ResponseEntityManager.ok(responseDtos);
    }
}
