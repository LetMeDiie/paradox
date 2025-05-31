package kz.amixady.paradox.task.service.impl;


import kz.amixady.paradox.task.api.dto.request.UpdateTaskDetailsRequest;
import kz.amixady.paradox.task.api.dto.response.TaskResponse;
import kz.amixady.paradox.task.exception.TaskAccessDeniedException;
import kz.amixady.paradox.task.exception.TaskNotFoundException;
import kz.amixady.paradox.task.mapper.TaskMapper;
import kz.amixady.paradox.task.persistence.entities.Task;
import kz.amixady.paradox.task.persistence.repo.TaskRepository;
import kz.amixady.paradox.task.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.function.Consumer;


@Service
@RequiredArgsConstructor
@Slf4j
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;


    @Override
    public TaskResponse updateTaskDetails(UUID userId, UUID taskId, UpdateTaskDetailsRequest request) {
        var task = findTaskByIdOrThrow(userId, taskId);

        // Утилитарный метод для обновления поля, если значение не null
        updateIfNotNull(task.getTaskDetails()::setTitle, request.title());
        updateIfNotNull(task.getTaskDetails()::setDescription, request.description());

        // Для enum поля — обычная проверка, так как нельзя использовать Consumer<Enum> напрямую из-за разного типа
        if (request.status() != null) {
            task.getTaskDetails().setStatus(request.status());
        }

        var savedTask = taskRepository.save(task);
        return taskMapper.fromTask(savedTask);
    }

    @Override
    public TaskResponse findTaskById(UUID userId, UUID taskId) {
        var task =
                findTaskByIdOrThrow(userId,taskId);

        return taskMapper.fromTask(task);
    }

    @Override
    public void deleteTask(UUID userId, UUID taskId){
        var task =
                findTaskByIdOrThrow(userId,taskId);

        taskRepository.delete(task);
    }

    private Task findTaskByIdOrThrow(UUID userId, UUID taskId){
        // Ищем задачу по id, если не найдена — кидаем исключение
        var task =
                taskRepository.findById(taskId)
                        .orElseThrow(()-> new TaskNotFoundException(taskId.toString()));
        // Проверяем, что пользователь владеет задачей, если нет — кидаем исключение доступа
        if(!userId.equals(task.getUserId())) {
            throw new TaskAccessDeniedException();
        }
        return task;
    }

    // Вспомогательный метод, обновляет поле через setter, если value не null
    private <T> void updateIfNotNull(Consumer<T> setter, T value) {
        if (value != null) {
            setter.accept(value);
        }
    }
}
