package kz.amixady.paradox.user.service;

import kz.amixady.paradox.user.api.dto.request.UserUpdateRequest;
import kz.amixady.paradox.user.api.dto.response.UserResponse;

public interface UserProfileService {
    UserResponse findByUsername(String username);
    UserResponse updateUserProfile(String username, UserUpdateRequest request);
    void deleteUser(String username);

}
