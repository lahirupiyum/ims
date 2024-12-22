package com.lahiru.ims.feature.customer.lastmile.media;

import com.lahiru.ims.common.controller.GenericBasicInfoController;
import com.lahiru.ims.common.dto.StandardReponse;
import com.lahiru.ims.feature.customer.lastmile.media.dto.LastMileMediaDto;
import com.lahiru.ims.utils.ResponseEntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("${endpoints.last-mile-media}")
@RequiredArgsConstructor
public class LastMileMediaController implements GenericBasicInfoController<LastMileMediaDto> {
    private final LastMileMediaService lastMileMediaService;

    @Override
    public ResponseEntity<StandardReponse<List<LastMileMediaDto>>> getAll() throws Exception {
        List<LastMileMediaDto> all = lastMileMediaService.getAll();
        return ResponseEntityManager.ok(all);
    }
}
