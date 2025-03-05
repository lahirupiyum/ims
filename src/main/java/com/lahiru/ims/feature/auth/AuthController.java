package com.lahiru.ims.feature.auth;

import com.lahiru.ims.feature.auth.dto.AuthRequestDto;
import com.lahiru.ims.feature.auth.dto.AuthResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${endpoints.auth}")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping
    public ResponseEntity<AuthResponseDto> authenticateUser(@Valid @RequestBody AuthRequestDto authRequestDto) throws Exception {
        AuthResponseDto authResponseDto = authService.authenticate(authRequestDto);
        return ResponseEntity.ok(authResponseDto);
    }
}
