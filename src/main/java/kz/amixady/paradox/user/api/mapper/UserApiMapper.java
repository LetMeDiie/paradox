package kz.amixady.paradox.user.api.mapper;

import kz.amixady.paradox.user.api.dto.request.UserCreateRequest;
import kz.amixady.paradox.user.api.dto.response.UserResponse;
import kz.amixady.paradox.user.persistence.entities.User;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;


@Component
public class UserApiMapper {

    public User toEntity(UserCreateRequest request){
        return User.builder()
                .username(request.username())
                .firstname(request.firstname())
                .lastname(request.lastname())
                .password(request.password())
                .build();
    }

    public UserResponse toResponse(User user) {
        Set<String> roles = user.getRoles()
                .stream()
                .map(role -> role.getRoleName().toString())
                .collect(Collectors.toSet());

        return new UserResponse(
                user.getId(),
                user.getFirstname(),
                user.getLastname(),
                user.getUsername(),
                user.getCreatedAt(),
                roles
        );
    }

}
