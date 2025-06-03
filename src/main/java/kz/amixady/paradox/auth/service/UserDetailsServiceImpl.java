package kz.amixady.paradox.auth.service;

import kz.amixady.paradox.auth.CustomUserDetails;
import kz.amixady.paradox.user.exception.UserNotFoundException;
import kz.amixady.paradox.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            log.info("Загрузка пользователя по имени: {}", username);
            var user =
                    userService.findUserByUsername(username);
            return new CustomUserDetails(user);
        }
        catch (UserNotFoundException exception) {
            throw new UsernameNotFoundException(exception.getLocalizedMessage());
        }
    }
}