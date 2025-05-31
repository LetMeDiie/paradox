package kz.amixady.paradox.auth.jwt;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class JwtTokenParser {

    private final JwtKeyProvider jwtKeyProvider;


    public UUID getUserIdFromToken(String token) {
        String subject =
                getClaims(token).getSubject();
        return UUID.fromString(subject);
    }

    public List<String> getRolesFromAccessToken(String token) {
        return getClaims(token).get("roles", List.class);
    }

    public Optional<TokenType> getTokenType(String token) {
        try {
            String typeStr = getClaims(token).get("type", String.class);
            return Optional.of(TokenType.valueOf(typeStr));
        } catch (IllegalArgumentException | NullPointerException e) {
            return Optional.empty();
        }
    }

    public boolean isValid(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(jwtKeyProvider.getKey())
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(jwtKeyProvider.getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

}
