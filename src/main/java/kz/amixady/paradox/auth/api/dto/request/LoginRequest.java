package kz.amixady.paradox.auth.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;


public record LoginRequest(

        @NotBlank(message = "Имя пользователя не может быть пустым")
        @Size(min = 3, max = 30, message = "Имя пользователя должно быть от 3 до 30 символов")
        @Pattern(regexp = "^[a-zA-Z0-9_.-]+$", message = "Имя пользователя может содержать только латинские буквы, цифры и символы . _ -")
        String username,

        @NotBlank(message = "Пароль не может быть пустым")
        @Size(min = 6, max = 100, message = "Пароль должен быть от 6 до 100 символов")
        @Pattern(
                regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$",
                message = "Пароль должен содержать хотя бы одну заглавную букву, одну строчную букву и одну цифру"
        )
        String password
) {
}
