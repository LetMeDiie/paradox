package kz.amixady.paradox.task.api.dto.response;


import kz.amixady.paradox.task.enums.TaskStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record TaskResponse(
        UUID id,
        UUID userId,
        String title,
        String description,
        TaskStatus taskStatus,
        LocalDateTime createdAt
) {
}
