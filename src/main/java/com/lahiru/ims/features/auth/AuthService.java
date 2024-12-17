package com.lahiru.ims.features.auth;

import com.lahiru.ims.features.auth.dto.AuthRequestDto;
import com.lahiru.ims.features.auth.dto.AuthResponseDto;

public interface AuthService {
    AuthResponseDto authenticate(AuthRequestDto authRequestDto) throws Exception;
}
