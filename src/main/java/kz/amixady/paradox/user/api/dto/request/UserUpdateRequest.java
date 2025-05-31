package kz.amixady.paradox.user.api.dto.request;


import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserUpdateRequest(
        @Size(min = 2, max = 50, message = "Имя должно быть от 2 до 50 символов")
        @Pattern(regexp = "^[A-Za-zА-Яа-яЁё\\-\\s]+$", message = "Имя может содержать только буквы, пробелы и дефисы")
        String firstname,

        @Size(min = 2, max = 50, message = "Фамилия должна быть от 2 до 50 символов")
        @Pattern(regexp = "^[A-Za-zА-Яа-яЁё\\-\\s]+$", message = "Фамилия может содержать только буквы, пробелы и дефисы")
        String lastname
) {
}
