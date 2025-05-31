package kz.amixady.paradox.task.service.impl;



import kz.amixady.paradox.task.api.dto.request.TaskDetailsCreateRequest;
import kz.amixady.paradox.task.api.dto.request.TaskSearchRequest;
import kz.amixady.paradox.task.api.dto.response.TaskResponse;
import kz.amixady.paradox.task.mapper.TaskDetailsMapper;
import kz.amixady.paradox.task.mapper.TaskMapper;
import kz.amixady.paradox.task.persistence.entities.Task;
import kz.amixady.paradox.task.persistence.repo.TaskRepository;
import kz.amixady.paradox.task.service.TaskFilter;
import kz.amixady.paradox.task.service.TasksService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class TasksServiceImpl implements TasksService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final TaskDetailsMapper taskDetailsMapper;
    private final TaskFilter taskFilter;

    @Override
    public TaskResponse createTask(UUID userId, TaskDetailsCreateRequest request) {
        var taskDetails =
                taskDetailsMapper.toTaskDetails(request);

        var task = Task.builder()
                .taskDetails(taskDetails)
                .userId(userId)
                .build();

        var savedTask = taskRepository.save(task);

        return taskMapper.fromTask(savedTask);
    }



    @Override
    public List<TaskResponse> findAll(UUID userId) {
        var tasks = taskRepository.findByUserId(userId);
        return tasks.stream()
                .map(taskMapper::fromTask)
                .toList();
    }

    @Override
    public List<TaskResponse> search(UUID userId, TaskSearchRequest request) {
        var spec =
                taskFilter.build(userId,request);

        var results = taskRepository.findAll(spec);

        return results.stream()
                .map(taskMapper::fromTask)
                .toList();
    }
}
