package kz.amixady.paradox.user.api.dto.request;

public record UserCreateRequest(
        String firstname,
        String lastname,
        String username,
        String password

) {
}
