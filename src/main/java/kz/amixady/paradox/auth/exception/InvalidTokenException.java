package kz.amixady.paradox.auth.exception;

import kz.amixady.paradox.handler.AppException;
import org.springframework.http.HttpStatus;

public class InvalidTokenException extends AppException {

    public InvalidTokenException(String message) {
        super(message, HttpStatus.UNAUTHORIZED);
    }
}
