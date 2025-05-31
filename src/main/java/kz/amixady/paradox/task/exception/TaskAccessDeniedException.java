package kz.amixady.paradox.task.exception;

import kz.amixady.paradox.handler.AppException;
import org.springframework.http.HttpStatus;

public class TaskAccessDeniedException extends AppException {


    public TaskAccessDeniedException() {
        super("Доступ к задаче запрещён", HttpStatus.FORBIDDEN);
    }
}
