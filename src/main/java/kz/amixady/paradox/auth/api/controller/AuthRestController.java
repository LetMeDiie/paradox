package kz.amixady.paradox.auth.api.controller;


import jakarta.validation.Valid;
import kz.amixady.paradox.auth.api.dto.request.LoginRequest;
import kz.amixady.paradox.auth.api.dto.request.RegisterRequest;
import kz.amixady.paradox.auth.api.dto.request.TokenRefreshRequest;
import kz.amixady.paradox.auth.api.dto.response.AuthResponse;
import kz.amixady.paradox.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthRestController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }


    @PostMapping("/register")
    public ResponseEntity<String> register(
            @Valid @RequestBody RegisterRequest request){
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthResponse> refresh(
           @Valid @RequestBody TokenRefreshRequest request){
        return ResponseEntity.ok(authService.refresh(request));
    }

}