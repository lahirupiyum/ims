package com.lahiru.ims.common.controller;

import com.lahiru.ims.common.dto.StandardReponse;
import com.lahiru.ims.common.dto.feature.AssetRequestDto;
import com.lahiru.ims.common.dto.feature.AssetResponseDto;
import com.lahiru.ims.feature.inventory.model.dto.ModelDto;
import com.lahiru.ims.feature.inventory.status.dto.StatusDto;
import com.lahiru.ims.feature.inventory.type.dto.TypeDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface GenericAssetController<RequestDto extends AssetRequestDto, ResponseDto extends AssetResponseDto> extends GenericController<RequestDto, ResponseDto>{
    @GetMapping("/model/all")
    ResponseEntity<StandardReponse<List<ModelDto>>> getAllModels() throws Exception;

    @GetMapping("/type/all")
    ResponseEntity<StandardReponse<List<TypeDto>>> getAllTypes() throws Exception;

    @GetMapping("/status/all")
    ResponseEntity<StandardReponse<List<StatusDto>>> getAllStatus() throws Exception;

    @GetMapping("/search")
    ResponseEntity<StandardReponse<List<ResponseDto>>> search(@RequestParam("key") String key) throws Exception;
}
