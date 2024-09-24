package com.lahiru.ims.branch;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lahiru.ims.branch.dto.BranchRequestDto;
import com.lahiru.ims.branch.dto.BranchResponseDto;
import com.lahiru.ims.common.GlobalController;
import com.lahiru.ims.common.dto.PaginationResponse;
import com.lahiru.ims.common.dto.StandardReponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/${application.resource.branch}")
@RequiredArgsConstructor
public class BranchController implements GlobalController<BranchRequestDto, BranchResponseDto> {

    private final BranchSerivce branchSerivce;
    private final Logger logger = LoggerFactory.getLogger(BranchController.class);
    private final int httpOkStatusCode = HttpStatus.OK.value();

    @Override
    public ResponseEntity<PaginationResponse<BranchResponseDto>> getAllByPageWise(int page,
            int pageSize) throws Exception {
        try {
            return ResponseEntity.ok().body(branchSerivce.findByPageWise(page, pageSize));
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

    @Override
    public ResponseEntity<StandardReponse<List<BranchResponseDto>>> getAll() throws Exception {
        try {
            return ResponseEntity.ok().body(new StandardReponse<>(httpOkStatusCode, "", branchSerivce.findAll()));
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

    @Override
    public ResponseEntity<StandardReponse<BranchResponseDto>> createOne(@Valid BranchRequestDto requestDto)
            throws Exception {
        try {
            BranchResponseDto responseDto = branchSerivce.createOne(requestDto);
            return ResponseEntity.status(HttpStatus.CREATED.value()).body(new StandardReponse<>(HttpStatus.CREATED.value(), "",responseDto));
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

    @Override
    public ResponseEntity<StandardReponse<BranchResponseDto>> updateOne(int id, @Valid BranchRequestDto requestDto)
            throws Exception {
                try {
                    BranchResponseDto responseDto = branchSerivce.updateOne(id, requestDto);
                    return ResponseEntity.ok().body(new StandardReponse<>(responseDto));
                } catch (Exception e) {
                    logger.error(e.getMessage());
                    throw e;
                }
    }

    @Override
    public ResponseEntity<StandardReponse<BranchResponseDto>> deleteOne(int id) throws Exception {
        try {
            BranchResponseDto responseDto = branchSerivce.deleteOne(id);
            return ResponseEntity.ok().body(new StandardReponse<>(responseDto));
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }
    
}
