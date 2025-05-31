package kz.amixady.paradox.task.service;




import kz.amixady.paradox.task.api.dto.request.TaskDetailsCreateRequest;
import kz.amixady.paradox.task.api.dto.request.TaskSearchRequest;
import kz.amixady.paradox.task.api.dto.response.TaskResponse;

import java.util.List;
import java.util.UUID;

public interface TasksService {
    TaskResponse createTask(UUID userId, TaskDetailsCreateRequest request);
    List<TaskResponse> findAll(UUID userId);
    List<TaskResponse> search(UUID userId, TaskSearchRequest request);


}
