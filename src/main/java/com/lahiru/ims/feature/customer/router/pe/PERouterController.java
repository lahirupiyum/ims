package com.lahiru.ims.feature.customer.router.pe;

import com.lahiru.ims.common.controller.GenericController;
import com.lahiru.ims.common.dto.PaginationResponse;
import com.lahiru.ims.common.dto.StandardReponse;
import com.lahiru.ims.exception.ValidationException;
import com.lahiru.ims.feature.customer.router.pe.dto.PERouterRequestDto;
import com.lahiru.ims.feature.customer.router.pe.dto.PERouterResponseDto;
import com.lahiru.ims.utils.ResponseEntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("${endpoints.router-provider-edge")
@RequiredArgsConstructor
public class PERouterController implements GenericController<PERouterRequestDto, PERouterResponseDto> {
    private final PERouterService peRouterService;

    @Override
    public ResponseEntity<PaginationResponse<PERouterResponseDto>> getAllByPageWise(int page, int pageSize) throws Exception {
        PaginationResponse<PERouterResponseDto> byPageWise = peRouterService.findByPageWise(page, pageSize);
        return ResponseEntityManager.page(byPageWise);
    }

    @Override
    public ResponseEntity<StandardReponse<List<PERouterResponseDto>>> getAll() throws Exception {
        List<PERouterResponseDto> all = peRouterService.findAll();
        return ResponseEntityManager.ok(all);
    }

    @Override
    public ResponseEntity<StandardReponse<PERouterResponseDto>> createOne(PERouterRequestDto peRouterRequestDto) throws Exception {
        PERouterResponseDto created = peRouterService.createOne(peRouterRequestDto);
        return ResponseEntityManager.created(created, "PE Router");
    }

    @Override
    public ResponseEntity<StandardReponse<PERouterResponseDto>> updateOne(int id, PERouterRequestDto peRouterRequestDto) throws Exception {
        PERouterResponseDto peRouterResponseDto = peRouterService.updateOne(id, peRouterRequestDto);
        return ResponseEntityManager.ok(peRouterResponseDto);
    }

    @Override
    public ResponseEntity<StandardReponse<PERouterResponseDto>> deleteOne(int id) throws Exception {
        throw new ValidationException("You can't perform delete on pe routes!");
    }
}
