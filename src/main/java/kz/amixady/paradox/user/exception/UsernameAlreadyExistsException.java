package kz.amixady.paradox.user.exception;

import kz.amixady.paradox.handler.AppException;
import org.springframework.http.HttpStatus;

public class UsernameAlreadyExistsException extends AppException {

    public UsernameAlreadyExistsException(String username) {
        super("Имя пользователя уже существует: " + username, HttpStatus.CONFLICT);
    }
}
