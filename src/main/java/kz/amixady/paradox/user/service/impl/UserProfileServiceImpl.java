package kz.amixady.paradox.user.service.impl;

import kz.amixady.paradox.user.exception.UserNotFoundException;
import kz.amixady.paradox.user.api.dto.request.UserUpdateRequest;
import kz.amixady.paradox.user.api.dto.response.UserResponse;
import kz.amixady.paradox.user.api.mapper.UserApiMapper;
import kz.amixady.paradox.user.persistence.entities.User;
import kz.amixady.paradox.user.persistence.repo.UserRepository;
import kz.amixady.paradox.user.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class UserProfileServiceImpl implements UserProfileService {

    private final UserRepository userRepository;
    private final UserApiMapper userApiMapper;


    @Override
    public UserResponse findByUsername(String username) {
        log.info("Запрос на поиск пользователя с username='{}'", username);

        var user =
                findUserByUsernameOrThrow(username);

        log.debug("Пользователь найден: {}", user);

        return userApiMapper
                .toResponse(user);
    }

    @Override
    public UserResponse updateUserProfile(String username, UserUpdateRequest request) {
        var user =
                findUserByUsernameOrThrow(username);

        if(request.firstname()!=null){
            user.setFirstname(request.firstname());
        }

        if(request.lastname()!=null) {
            user.setLastname(request.lastname());
        }

        var savedUser =
                userRepository.save(user);
        log.info("Профиль пользователя '{}' успешно обновлен", username);


        return userApiMapper.toResponse(savedUser);
    }

    @Override
    public void deleteUser(String username) {
        var user =
                findUserByUsernameOrThrow(username);
        userRepository.delete(user);
    }

    private User findUserByUsernameOrThrow(String username){
        log.debug("Поиск пользователя по username='{}'", username);
        return userRepository.findByUsername(username)
                .orElseThrow(() -> {
                    log.warn("Пользователь с username='{}' не найден", username);
                    return new UserNotFoundException(username);
                });
    }
}
