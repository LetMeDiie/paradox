package kz.amixady.paradox.user.api.dto.response;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

public record UserResponse(
        UUID id,
        String firstname,
        String lastname,
        String username,
        LocalDateTime createdAt,
        Set<String> roles
) {
}
