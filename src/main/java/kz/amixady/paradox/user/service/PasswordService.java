package kz.amixady.paradox.user.service;

import kz.amixady.paradox.user.api.dto.request.ChangePasswordRequest;

public interface PasswordService {
    void changePassword(String username, ChangePasswordRequest request);
}
