package com.lahiru.ims.feature.customer.lastmile.provider;

import com.lahiru.ims.common.controller.GenericBasicInfoController;
import com.lahiru.ims.common.dto.StandardReponse;
import com.lahiru.ims.feature.customer.lastmile.provider.dto.LastMileProviderDto;
import com.lahiru.ims.utils.ResponseEntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("${endpoints.last-mile-provider}")
@RequiredArgsConstructor
public class LastMileProviderController implements GenericBasicInfoController<LastMileProviderDto> {
    private final LastMileProviderService lastMileProviderService;

    @Override
    public ResponseEntity<StandardReponse<List<LastMileProviderDto>>> getAll() throws Exception {
        List<LastMileProviderDto> all = lastMileProviderService.getAll();
        return ResponseEntityManager.ok(all);
    }
}
