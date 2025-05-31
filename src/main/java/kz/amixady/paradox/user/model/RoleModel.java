package kz.amixady.paradox.user.model;

import kz.amixady.paradox.user.enums.RoleName;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

@Getter
@Setter
public class RoleModel implements GrantedAuthority {

    private RoleName roleName;

    public RoleModel(RoleName roleName) {
        this.roleName = roleName;
    }

    @Override
    public String getAuthority() {
        return roleName.name();
    }
}
