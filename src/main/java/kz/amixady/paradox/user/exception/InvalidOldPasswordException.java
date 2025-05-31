package kz.amixady.paradox.user.exception;


import kz.amixady.paradox.handler.AppException;
import org.springframework.http.HttpStatus;

public class InvalidOldPasswordException extends AppException {
    public InvalidOldPasswordException() {
        super("Old password is incorrect", HttpStatus.BAD_REQUEST);
    }
}