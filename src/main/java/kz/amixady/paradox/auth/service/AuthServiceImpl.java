package kz.amixady.paradox.auth.service;

import kz.amixady.paradox.auth.api.dto.request.LoginRequest;
import kz.amixady.paradox.auth.api.dto.request.RegisterRequest;
import kz.amixady.paradox.auth.api.dto.request.TokenRefreshRequest;
import kz.amixady.paradox.auth.api.dto.response.AuthResponse;
import kz.amixady.paradox.auth.exception.CustomAuthenticationException;
import kz.amixady.paradox.auth.exception.InvalidTokenException;
import kz.amixady.paradox.auth.exception.PasswordsDoNotMatchException;
import kz.amixady.paradox.auth.api.mapper.AuthRequestMapper;
import kz.amixady.paradox.auth.jwt.JwtTokenParser;
import kz.amixady.paradox.auth.jwt.TokenType;
import kz.amixady.paradox.user.exception.UsernameAlreadyExistsException;
import kz.amixady.paradox.auth.jwt.JwtTokenProvider;
import kz.amixady.paradox.user.model.UserModel;
import kz.amixady.paradox.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final AuthRequestMapper authRequestMapper;
    private final JwtTokenProvider jwtTokenProvider;
    private final JwtTokenParser jwtTokenParser;

    @Override
    public AuthResponse login(LoginRequest request) {

        log.info("Попытка входа для пользователя: {}", request.username());
        try {
            authenticationManager.
                    authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    request.username(), request.password()));
        }
        catch (BadCredentialsException exception){
            throw new CustomAuthenticationException();
        }
        log.info("Аутентификация успешна для пользователя: {}", request.username());

        var userModel =
                userService.findUserByUsername(request.username());

        return generateTokens(userModel);
    }

    @Override
    public AuthResponse refresh(TokenRefreshRequest request) {
        log.info("Попытка обновить токен: {}", request.refresh());

        if(!jwtTokenParser.isValid(request.refresh())) {
            log.warn("Невалидный refresh токен: {}", request.refresh());
            throw new InvalidTokenException("Недопустимый токен");
        }

        var tokenType = jwtTokenParser.getTokenType(request.refresh())
                .orElseThrow(() -> {
                    log.warn("Не удалось определить тип токена: {}", request.refresh());
                    return new InvalidTokenException("Нужен refresh токен");
                });

        if(tokenType!= TokenType.REFRESH) {
            log.warn("Передан токен не типа REFRESH: {}", tokenType);
            throw new InvalidTokenException("Нужен refresh токен");
        }

        UUID userId = jwtTokenParser
                .getUserIdFromToken(request.refresh());
        var userModel =
                userService.findByUserId(userId);

        log.info("Обновление токена для пользователя: {}", userModel.getUsername());

        return generateTokens(userModel);
    }


    @Override
    public String register(RegisterRequest request) {
        log.info("Регистрация пользователя: {}", request.username());
        var userModel =
                registerUser(request);
        log.info("Регистрация успешна: {}", request.username());

        return "Регистрация успешна для пользователя: "+request.username();
    }



    private UserModel registerUser(RegisterRequest request) {
        if(!request.password().equals(request.confirmPassword())) {
            throw new PasswordsDoNotMatchException();
        }

        if(userService.existsByUsername(request.username())) {
            throw new UsernameAlreadyExistsException(request.username());
        }

        var userCreatedRequest =
                authRequestMapper
                        .toUserCreateRequest(request,passwordEncoder.encode(request.password()));

        return userService.createUser(userCreatedRequest);

    }

    private AuthResponse generateTokens(UserModel userModel) {
        String accessToken =
                jwtTokenProvider.createAccessToken(userModel);
        String refreshToken =
                jwtTokenProvider.createRefreshToken(userModel);
        return new AuthResponse(accessToken, refreshToken);
    }
}
