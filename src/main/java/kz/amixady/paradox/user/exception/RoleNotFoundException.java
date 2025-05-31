package kz.amixady.paradox.user.exception;

import kz.amixady.paradox.handler.AppException;
import kz.amixady.paradox.user.enums.RoleName;
import org.springframework.http.HttpStatus;

public class RoleNotFoundException extends AppException {
    public RoleNotFoundException(RoleName roleName) {
        super("Can not found role:"+ roleName.toString(), HttpStatus.NOT_FOUND);
    }
}
