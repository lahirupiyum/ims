package com.lahiru.ims.features.auth.impl;

import com.lahiru.ims.features.auth.AuthService;
import com.lahiru.ims.features.auth.dto.AuthRequestDto;
import com.lahiru.ims.features.auth.dto.AuthResponseDto;
import com.lahiru.ims.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @Override
    public AuthResponseDto authenticate(AuthRequestDto authRequestDto) throws Exception {
        Authentication authentication = new UsernamePasswordAuthenticationToken(authRequestDto.getUsername(), authRequestDto.getPassword());
        authenticationManager.authenticate(authentication);
        String token = jwtUtil.generateToken(authentication);
        return new AuthResponseDto(token);
    }
}
