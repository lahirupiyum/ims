package com.lahiru.ims.feature.customer.router.customer;

import com.lahiru.ims.common.controller.GenericController;
import com.lahiru.ims.common.dto.PaginationResponse;
import com.lahiru.ims.common.dto.StandardReponse;
import com.lahiru.ims.feature.customer.router.customer.dto.CusRouterRequestDto;
import com.lahiru.ims.feature.customer.router.customer.dto.CusRouterResponseDto;
import com.lahiru.ims.utils.ResponseEntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("${endpoints.router-customer}")
@RequiredArgsConstructor
public class CusRouterController implements GenericController<CusRouterRequestDto, CusRouterResponseDto> {
    public static final String CUSTOMER_ROUTER = "Customer Router";
    private final CusRouterService cusRouterService;

    @Override
    public ResponseEntity<PaginationResponse<CusRouterResponseDto>> getAllByPageWise(int page, int pageSize) throws Exception {
        PaginationResponse<CusRouterResponseDto> byPageWise = cusRouterService.findByPageWise(page, pageSize);
        return ResponseEntityManager.page(byPageWise);
    }

    @Override
    public ResponseEntity<StandardReponse<List<CusRouterResponseDto>>> getAll() throws Exception {
        List<CusRouterResponseDto> all = cusRouterService.findAll();
        return ResponseEntityManager.ok(all);
    }

    @Override
    public ResponseEntity<StandardReponse<CusRouterResponseDto>> createOne(CusRouterRequestDto cusRouterRequestDto) throws Exception {
        CusRouterResponseDto createdRouter = cusRouterService.createOne(cusRouterRequestDto);
        return ResponseEntityManager.created(createdRouter, CUSTOMER_ROUTER);
    }

    @Override
    public ResponseEntity<StandardReponse<CusRouterResponseDto>> updateOne(int id, CusRouterRequestDto cusRouterRequestDto) throws Exception {
        CusRouterResponseDto updatedRouter = cusRouterService.updateOne(id, cusRouterRequestDto);
        return ResponseEntityManager.updated(updatedRouter, CUSTOMER_ROUTER);
    }

    @Override
    public ResponseEntity<StandardReponse<CusRouterResponseDto>> deleteOne(int id) throws Exception {
        CusRouterResponseDto deletedRouter = cusRouterService.deleteOne(id);
        return ResponseEntityManager.deleted(deletedRouter, CUSTOMER_ROUTER);
    }
}
