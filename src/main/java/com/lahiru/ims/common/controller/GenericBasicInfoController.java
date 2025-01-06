package com.lahiru.ims.common.controller;

import com.lahiru.ims.common.dto.StandardReponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface GenericBasicInfoController <Dto> {
    @GetMapping("/all")
    ResponseEntity<StandardReponse<List<Dto>>> getAll() throws Exception;
    @GetMapping("/search")
    ResponseEntity<StandardReponse<List<Dto>>> searchByName(@RequestParam("key") String key) throws Exception;
}
