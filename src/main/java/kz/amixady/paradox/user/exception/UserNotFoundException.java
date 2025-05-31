package kz.amixady.paradox.user.exception;

import kz.amixady.paradox.handler.AppException;
import org.springframework.http.HttpStatus;

public class UserNotFoundException extends AppException {
    public UserNotFoundException (String username) {
        super("User already exists: " + username, HttpStatus.NOT_FOUND);
    }
}
