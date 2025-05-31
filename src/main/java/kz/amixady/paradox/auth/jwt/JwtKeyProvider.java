package kz.amixady.paradox.auth.jwt;

import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;

@Component
@Getter
public class JwtKeyProvider {

    private final JwtProperties jwtProperties;
    private Key key;

    public JwtKeyProvider(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
        init();
    }

    private void init() {
        this.key = Keys.hmacShaKeyFor(jwtProperties.getSecret().getBytes(StandardCharsets.UTF_8));
    }
}
