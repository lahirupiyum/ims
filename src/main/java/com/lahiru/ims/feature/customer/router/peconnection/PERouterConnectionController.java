package com.lahiru.ims.feature.customer.router.peconnection;

import com.lahiru.ims.common.controller.GenericController;
import com.lahiru.ims.common.dto.PaginationResponse;
import com.lahiru.ims.common.dto.StandardReponse;
import com.lahiru.ims.feature.customer.router.peconnection.dto.PERouterConnectionResponseDto;
import com.lahiru.ims.feature.customer.router.peconnection.dto.PERouterConnectionRequestDto;
import com.lahiru.ims.utils.ResponseEntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("${endpoints.router-provider-edge}")
@RequiredArgsConstructor
public class PERouterConnectionController implements GenericController<PERouterConnectionRequestDto, PERouterConnectionResponseDto> {
    private final PERouterConnectionService peRouterConnectionService;

    @Override
    public ResponseEntity<PaginationResponse<PERouterConnectionResponseDto>> getAllByPageWise(int page, int pageSize) throws Exception {
        PaginationResponse<PERouterConnectionResponseDto> byPageWise = peRouterConnectionService.findByPageWise(page, pageSize);
        return ResponseEntityManager.page(byPageWise);
    }

    @Override
    public ResponseEntity<StandardReponse<List<PERouterConnectionResponseDto>>> getAll() throws Exception {
        List<PERouterConnectionResponseDto> all = peRouterConnectionService.findAll();
        return ResponseEntityManager.ok(all);
    }

    @Override
    public ResponseEntity<StandardReponse<PERouterConnectionResponseDto>> createOne(PERouterConnectionRequestDto peRouterRequestDto) throws Exception {
        PERouterConnectionResponseDto createdPERouter = peRouterConnectionService.createOne(peRouterRequestDto);
        return ResponseEntityManager.created(createdPERouter, "Provider edge");
    }

    @Override
    public ResponseEntity<StandardReponse<PERouterConnectionResponseDto>> updateOne(int id, PERouterConnectionRequestDto peRouterRequestDto) throws Exception {
        PERouterConnectionResponseDto updatedRouter = peRouterConnectionService.updateOne(id, peRouterRequestDto);
        return ResponseEntityManager.ok(updatedRouter);
    }

    @Override
    public ResponseEntity<StandardReponse<PERouterConnectionResponseDto>> deleteOne(int id) throws Exception {
        PERouterConnectionResponseDto deletedRouter = peRouterConnectionService.deleteOne(id);
        return ResponseEntityManager.ok(deletedRouter);
    }
}
