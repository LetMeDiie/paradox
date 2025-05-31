package kz.amixady.paradox.user.service.impl;

import kz.amixady.paradox.user.exception.RoleNotFoundException;
import kz.amixady.paradox.user.enums.RoleName;
import kz.amixady.paradox.user.persistence.entities.Role;
import kz.amixady.paradox.user.persistence.repo.RoleRepository;
import kz.amixady.paradox.user.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Role findByRoleName(RoleName roleName) {
        return roleRepository.findByRoleName(roleName)
                .orElseThrow(()-> new RoleNotFoundException(roleName));
    }
}
