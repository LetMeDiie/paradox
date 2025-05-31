package kz.amixady.paradox.task.api.dto.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import kz.amixady.paradox.task.enums.TaskStatus;

public record TaskDetailsCreateRequest(
        @NotBlank(message = "Заголовок не должен быть пустым")
        @Size(min = 3, max = 50, message = "Длина заголовка должна быть от 3 до 50 символов")
        String title,

        @NotBlank(message = "Описание не должно быть пустым")
        @Size(min = 5, max = 500, message = "Длина описания должна быть от 5 до 500 символов")
        String description,

        @NotNull(message = "Статус не должен быть пустым")
        TaskStatus status
) {
}
