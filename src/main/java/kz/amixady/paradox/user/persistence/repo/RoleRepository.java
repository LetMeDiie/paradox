package kz.amixady.paradox.user.persistence.repo;

import kz.amixady.paradox.user.enums.RoleName;
import kz.amixady.paradox.user.persistence.entities.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends CrudRepository<Role,Integer> {
    Optional<Role> findByRoleName(RoleName roleName);
}
