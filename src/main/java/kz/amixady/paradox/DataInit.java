package kz.amixady.paradox;

import kz.amixady.paradox.user.enums.RoleName;
import kz.amixady.paradox.user.persistence.entities.Role;
import kz.amixady.paradox.user.persistence.repo.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataInit implements CommandLineRunner {

    private final RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        if (roleRepository.count() == 0) {
            var userRole =
                    Role.builder()
                            .roleName(RoleName.ROLE_USER)
                            .build();
            var adminRole =
                    Role.builder()
                            .roleName(RoleName.ROLE_ADMIN)
                            .build();

            roleRepository.save(userRole);
            roleRepository.save(adminRole);

            log.info("Роли добавлены");

        }
    }
}
