package kz.amixady.paradox.task.exception;

import kz.amixady.paradox.handler.AppException;
import org.springframework.http.HttpStatus;

public class TaskNotFoundException extends AppException {

    public TaskNotFoundException(String taskId) {
        super("Задача не найдена с Id: "+ taskId, HttpStatus.NOT_FOUND);
    }
}
