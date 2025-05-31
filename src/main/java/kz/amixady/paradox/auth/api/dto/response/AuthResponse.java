package kz.amixady.paradox.auth.api.dto.response;

public record AuthResponse(
        String accessToken,
        String refreshToken
) {
}
