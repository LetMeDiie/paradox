package kz.amixady.paradox.user.service;

import kz.amixady.paradox.user.enums.RoleName;
import kz.amixady.paradox.user.persistence.entities.Role;

public interface RoleService {
    Role findByRoleName(RoleName roleName);
}
