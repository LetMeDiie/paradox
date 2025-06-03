package kz.amixady.paradox.user.service.impl;

import jakarta.transaction.Transactional;
import kz.amixady.paradox.user.exception.UserNotFoundException;
import kz.amixady.paradox.user.exception.UsernameAlreadyExistsException;
import kz.amixady.paradox.user.api.dto.request.UserCreateRequest;
import kz.amixady.paradox.user.api.dto.response.UserResponse;
import kz.amixady.paradox.user.api.mapper.UserApiMapper;
import kz.amixady.paradox.user.enums.RoleName;
import kz.amixady.paradox.user.model.UserModel;
import kz.amixady.paradox.user.persistence.mapper.UserMapper;
import kz.amixady.paradox.user.persistence.repo.UserRepository;
import kz.amixady.paradox.user.service.RoleService;
import kz.amixady.paradox.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserApiMapper userApiMapper;
    private final RoleService roleService;
    private final UserMapper userMapper;

    @Override
    @Transactional
    public UserModel createUser(UserCreateRequest request) {

        if(userRepository.existsByUsername(request.username())){
            throw new UsernameAlreadyExistsException(request.username());
        }

        var user =
                userApiMapper.toEntity(request);

        var userRole =
                roleService.findByRoleName(RoleName.ROLE_ADMIN);
        user.setRoles(Set.of(userRole));

        var savedUser =
                userRepository.save(user);

        log.info("Пользователь успешно создан: {}", savedUser.getId());

        return userMapper.toModel(savedUser);
    }



    @Override
    public UserModel findUserByUsername(String username) {
        var userEntity =
                userRepository.findByUsername(username)
                        .orElseThrow(()-> new UserNotFoundException(username));
        return userMapper.toModel(userEntity);
    }

    @Override
    public UserModel findByUserId(UUID id) {
        var userEntity =
                userRepository.findById(id)
                        .orElseThrow(()-> new UserNotFoundException(id.toString()));
        return userMapper.toModel(userEntity);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }
}
