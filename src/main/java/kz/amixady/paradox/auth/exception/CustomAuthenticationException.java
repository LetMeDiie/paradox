package kz.amixady.paradox.auth.exception;

import kz.amixady.paradox.handler.AppException;
import org.springframework.http.HttpStatus;

public class CustomAuthenticationException extends AppException {

    public CustomAuthenticationException() {
        super("Ошибка аутентификации: неверное имя пользователя или пароль", HttpStatus.UNAUTHORIZED);
    }
}
