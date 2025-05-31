package kz.amixady.paradox.auth.service;

import kz.amixady.paradox.auth.api.dto.request.LoginRequest;
import kz.amixady.paradox.auth.api.dto.request.RegisterRequest;
import kz.amixady.paradox.auth.api.dto.request.TokenRefreshRequest;
import kz.amixady.paradox.auth.api.dto.response.AuthResponse;

public interface AuthService {

    AuthResponse login(LoginRequest request);

    String register(RegisterRequest request);

    AuthResponse refresh(TokenRefreshRequest request);
}

