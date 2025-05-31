package kz.amixady.paradox;


import jakarta.annotation.PostConstruct;
import kz.amixady.paradox.user.enums.RoleName;
import kz.amixady.paradox.user.persistence.entities.Role;
import kz.amixady.paradox.user.persistence.repo.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class RoleDataInitializer {

    private final RoleRepository roleRepository;

    @PostConstruct
    public void initRoles() {
        if (roleRepository.count() == 0) {
            log.info("Добавляем ролей");
            roleRepository.save(new Role(null, RoleName.ROLE_USER));
            roleRepository.save(new Role(null,RoleName.ROLE_ADMIN));
        }
    }
}