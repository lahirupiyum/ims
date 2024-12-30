package com.lahiru.ims.feature.customer.service.connection;

import com.lahiru.ims.common.controller.GenericController;
import com.lahiru.ims.common.dto.PaginationResponse;
import com.lahiru.ims.common.dto.StandardReponse;
import com.lahiru.ims.feature.customer.service.connection.dto.ConnectionRequestDto;
import com.lahiru.ims.feature.customer.service.connection.dto.ConnectionResponseDto;
import com.lahiru.ims.utils.ResponseEntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("${endpoints.service-connection}")
@RequiredArgsConstructor
public class ConnectionController implements GenericController<ConnectionRequestDto, ConnectionResponseDto> {
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
        return ResponseEntityManager.created(one, "Service Connection");
    }

    @Override
    public ResponseEntity<StandardReponse<ConnectionResponseDto>> updateOne(int id, ConnectionRequestDto connectionRequestDto) throws Exception {
        ConnectionResponseDto connectionResponseDto = connectionService.updateOne(id, connectionRequestDto);
        return ResponseEntityManager.ok(connectionResponseDto);
    }

    @Override
    public ResponseEntity<StandardReponse<ConnectionResponseDto>> deleteOne(int id) throws Exception {
        ConnectionResponseDto connectionResponseDto = connectionService.deleteOne(id);
        return ResponseEntityManager.ok(connectionResponseDto);
    }
}
