package kz.amixady.paradox.user.service.impl;

import kz.amixady.paradox.user.exception.InvalidOldPasswordException;
import kz.amixady.paradox.user.exception.UserNotFoundException;
import kz.amixady.paradox.user.api.dto.request.ChangePasswordRequest;
import kz.amixady.paradox.user.persistence.repo.UserRepository;
import kz.amixady.paradox.user.service.PasswordService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class PasswordServiceImpl implements PasswordService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void changePassword(String username, ChangePasswordRequest request) {
        log.info("Запрос на изменение пароля для пользователя: {}", username);

        var user = userRepository.findByUsername(username)
                .orElseThrow(() -> {
                    log.warn("Пользователь не найден: {}", username);
                    return new UserNotFoundException(username);
                });

        if (!passwordEncoder.matches(request.oldPassword(), user.getPassword())) {
            log.warn("Неверный старый пароль для пользователя: {}", username);
            throw new InvalidOldPasswordException();
        }

        user.setPassword(passwordEncoder.encode(request.newPassword()));
        userRepository.save(user);

        log.info("Пароль успешно изменён для пользователя: {}", username);
    }
}
