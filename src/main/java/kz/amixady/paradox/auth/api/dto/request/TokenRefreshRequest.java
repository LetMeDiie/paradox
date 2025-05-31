package kz.amixady.paradox.auth.api.dto.request;
import jakarta.validation.constraints.NotBlank;


public record TokenRefreshRequest(
        @NotBlank(message = "Refresh токен не должен быть пустым")
        String refresh
) {
}
