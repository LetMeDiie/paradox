package kz.amixady.paradox.task.mapper;


import kz.amixady.paradox.task.api.dto.response.TaskResponse;
import kz.amixady.paradox.task.persistence.entities.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

    public TaskResponse fromTask(Task task){
        return new TaskResponse(
           task.getId(),
           task.getUserId(),
           task.getTaskDetails().getTitle(),
           task.getTaskDetails().getDescription(),
           task.getTaskDetails().getStatus(),
           task.getCreatedAt()
        );
    }
}
