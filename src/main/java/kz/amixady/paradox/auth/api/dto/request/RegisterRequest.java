package kz.amixady.paradox.auth.api.dto.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record RegisterRequest(

        @NotBlank(message = "Имя пользователя не может быть пустым")
        @Size(min = 3, max = 30, message = "Имя пользователя должно быть от 3 до 30 символов")
        @Pattern(regexp = "^[a-zA-Z0-9_.-]+$", message = "Имя пользователя может содержать только латинские буквы, цифры и символы . _ -")
        String username,

        @NotBlank(message = "Имя не может быть пустым")
        @Size(min = 2, max = 50, message = "Имя должно быть от 2 до 50 символов")
        @Pattern(regexp = "^[A-Za-zА-Яа-яЁё\\-\\s]+$", message = "Имя может содержать только буквы, пробелы и дефисы")
        String firstname,

        @NotBlank(message = "Фамилия не может быть пустой")
        @Size(min = 2, max = 50, message = "Фамилия должна быть от 2 до 50 символов")
        @Pattern(regexp = "^[A-Za-zА-Яа-яЁё\\-\\s]+$", message = "Фамилия может содержать только буквы, пробелы и дефисы")
        String lastname,

        @NotBlank(message = "Пароль не может быть пустым")
        @Size(min = 6, max = 100, message = "Пароль должен быть от 6 до 100 символов")
        @Pattern(
                regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$",
                message = "Пароль должен содержать хотя бы одну заглавную букву, одну строчную букву и одну цифру"
        )
        String password,

        @NotBlank(message = "Подтверждение пароля не может быть пустым")
        String confirmPassword

) {}