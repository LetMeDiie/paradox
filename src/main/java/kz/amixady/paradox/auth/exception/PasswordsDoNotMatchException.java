package kz.amixady.paradox.auth.exception;


import kz.amixady.paradox.handler.AppException;
import org.springframework.http.HttpStatus;

public class PasswordsDoNotMatchException extends AppException {
    public PasswordsDoNotMatchException() {
        super("Введённые пароли не совпадают", HttpStatus.BAD_REQUEST);
    }
}