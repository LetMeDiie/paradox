package kz.amixady.paradox.user.service;

import kz.amixady.paradox.user.api.dto.request.UserCreateRequest;
import kz.amixady.paradox.user.model.UserModel;

import java.util.UUID;


public interface UserService {
    UserModel createUser(UserCreateRequest request);
    boolean existsByUsername(String username);
    UserModel findUserByUsername(String username);
    UserModel findByUserId(UUID id);
}
