package kz.amixady.paradox.task.persistence.repo;

import kz.amixady.paradox.task.persistence.entities.Task;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface TaskRepository extends CrudRepository<Task, UUID> , JpaSpecificationExecutor<Task>{
    List<Task> findByUserId(UUID userId);
}
