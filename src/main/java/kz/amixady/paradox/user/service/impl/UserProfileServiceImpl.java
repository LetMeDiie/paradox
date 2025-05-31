package kz.amixady.paradox.user.service.impl;

import kz.amixady.paradox.user.exception.UserNotFoundException;
import kz.amixady.paradox.user.api.dto.request.UserUpdateRequest;
import kz.amixady.paradox.user.api.dto.response.UserResponse;
import kz.amixady.paradox.user.api.mapper.UserApiMapper;
import kz.amixady.paradox.user.persistence.entities.User;
import kz.amixady.paradox.user.persistence.repo.UserRepository;
import kz.amixady.paradox.user.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserProfileServiceImpl implements UserProfileService {

    private final UserRepository userRepository;
    private final UserApiMapper userApiMapper;


    @Override
    public UserResponse findByUsername(String username) {
        var user =
                findUserByUsernameOrThrow(username);
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

        return userApiMapper.toResponse(savedUser);
    }

    @Override
    public void deleteUser(String username) {
        var user =
                findUserByUsernameOrThrow(username);
        userRepository.delete(user);
    }

    private User findUserByUsernameOrThrow(String username){
        return userRepository.findByUsername(username)
                .orElseThrow(()-> new UserNotFoundException(username));
    }
}
