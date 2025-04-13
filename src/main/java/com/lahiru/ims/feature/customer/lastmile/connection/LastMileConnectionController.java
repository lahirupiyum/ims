package com.lahiru.ims.feature.customer.lastmile.connection;

import com.lahiru.ims.common.dto.StandardReponse;
import com.lahiru.ims.feature.customer.lastmile.connection.dto.LastMileConnectionRequestDto;
import com.lahiru.ims.feature.customer.lastmile.connection.dto.LastMileConnectionResponseDto;
import com.lahiru.ims.utils.ResponseEntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${endpoints.last-mile-connection}")
@RequiredArgsConstructor
public class LastMileConnectionController {
    private final LastMileConnectionService lastMileConnectionService;

    @PutMapping("/{id}")
    public ResponseEntity<StandardReponse<LastMileConnectionResponseDto>> updateOne(@PathVariable("id") Integer id, @RequestBody LastMileConnectionRequestDto requestDto) throws Exception {
        LastMileConnection lastMileConnection = lastMileConnectionService.updateOne(id, requestDto);
        LastMileConnectionResponseDto responseDto = lastMileConnectionService.convertToDto(lastMileConnection);
        return ResponseEntityManager.updated(responseDto, "Last Mile Connection");
    }
}
