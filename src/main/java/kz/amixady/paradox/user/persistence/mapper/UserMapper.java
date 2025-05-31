package kz.amixady.paradox.user.persistence.mapper;


import kz.amixady.paradox.user.model.RoleModel;
import kz.amixady.paradox.user.model.UserModel;
import kz.amixady.paradox.user.persistence.entities.User;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public UserModel toModel(User user) {
        Set<RoleModel> roleModels = user.getRoles().stream()
                .map(role -> new RoleModel(role.getRoleName()))
                .collect(Collectors.toSet());

        return UserModel.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(roleModels)
                .build();
    }
}
