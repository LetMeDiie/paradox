package kz.amixady.paradox.task.service;




import kz.amixady.paradox.task.api.dto.request.UpdateTaskDetailsRequest;
import kz.amixady.paradox.task.api.dto.response.TaskResponse;

import java.util.UUID;

public interface TaskService {
    TaskResponse updateTaskDetails(UUID userId, UUID taskId, UpdateTaskDetailsRequest request);
    TaskResponse findTaskById(UUID userId, UUID taskId);
    void deleteTask(UUID userId,UUID taskId);
}
