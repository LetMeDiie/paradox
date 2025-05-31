package kz.amixady.paradox.task.mapper;


import kz.amixady.paradox.task.api.dto.request.TaskDetailsCreateRequest;
import kz.amixady.paradox.task.persistence.entities.TaskDetails;
import org.springframework.stereotype.Component;

@Component
public class TaskDetailsMapper {

    public TaskDetails toTaskDetails(TaskDetailsCreateRequest request){
        return TaskDetails.builder()
                .title(request.title())
                .description(request.description())
                .status(request.status())
                .build();
    }
}
