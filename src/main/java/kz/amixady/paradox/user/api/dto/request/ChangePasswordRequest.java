package kz.amixady.paradox.user.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ChangePasswordRequest(
        @NotBlank(message = "Пароль не может быть пустым")
        @Size(min = 6, max = 100, message = "Пароль должен быть от 6 до 100 символов")
        @Pattern(
                regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$",
                message = "Пароль должен содержать хотя бы одну заглавную букву, одну строчную букву и одну цифру"
        )
        String oldPassword,

        @NotBlank(message = "Пароль не может быть пустым")
        @Size(min = 6, max = 100, message = "Пароль должен быть от 6 до 100 символов")
        @Pattern(
                regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$",
                message = "Пароль должен содержать хотя бы одну заглавную букву, одну строчную букву и одну цифру"
        )
        String newPassword
) {
}
