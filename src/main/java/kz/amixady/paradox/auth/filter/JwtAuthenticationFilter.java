package kz.amixady.paradox.auth.filter;


import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kz.amixady.paradox.auth.CustomUserDetails;
import kz.amixady.paradox.auth.jwt.JwtTokenParser;
import kz.amixady.paradox.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenParser jwtTokenParser;
    private final UserService userService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        log.info("Начало проверки токена");

        String token = extractTokenFromHeader(request);

        if (token != null && jwtTokenParser.isValid(token)) {
            try {
                UUID userId =
                        jwtTokenParser.getUserIdFromToken(token);
                if (userId != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    var userModel =
                            userService.findByUserId(userId);

                    CustomUserDetails customUserDetails =
                            (CustomUserDetails) userDetailsService
                                    .loadUserByUsername(userModel.getUsername());

                    var auth = new UsernamePasswordAuthenticationToken(
                            customUserDetails,
                            null,
                            customUserDetails.getAuthorities()
                    );

                    SecurityContextHolder.getContext().setAuthentication(auth);

                    log.info("Токен успешно проверен и аутентификация установлена");
                    log.info("Аутентифицированный пользователь: {}", auth.getPrincipal());
                    log.info("Authorities: {}", auth.getAuthorities());
                }
            } catch (UsernameNotFoundException ex) {
                log.warn("Пользователь не найден: {}", ex.getMessage());
            } catch (JwtException ex) {
                log.warn("JWT токен невалидный: {}", ex.getMessage());
            }
        } else {
            log.info("Токен отсутствует или невалидный");
        }

        filterChain.doFilter(request, response);
    }


    private String extractTokenFromHeader(HttpServletRequest request) {
        String bearer = request.getHeader("Authorization");
        if (bearer != null && bearer.startsWith("Bearer ")) {
            return bearer.substring(7);
        }
        return null;
    }
}
