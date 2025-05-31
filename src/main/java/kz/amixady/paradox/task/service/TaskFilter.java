package kz.amixady.paradox.task.service;


import kz.amixady.paradox.task.api.dto.request.TaskSearchRequest;
import kz.amixady.paradox.task.persistence.entities.Task;
import org.springframework.data.jpa.domain.Specification;

import java.util.UUID;

public interface TaskFilter {
    Specification<Task> build(UUID userId, TaskSearchRequest request);
}
