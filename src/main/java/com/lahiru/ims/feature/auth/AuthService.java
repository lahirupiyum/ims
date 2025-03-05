package com.lahiru.ims.feature.auth;

import com.lahiru.ims.feature.auth.dto.AuthRequestDto;
import com.lahiru.ims.feature.auth.dto.AuthResponseDto;

public interface AuthService {
    AuthResponseDto authenticate(AuthRequestDto authRequestDto) throws Exception;
}
