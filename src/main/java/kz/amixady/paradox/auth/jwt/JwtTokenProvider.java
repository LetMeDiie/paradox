package kz.amixady.paradox.auth.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import kz.amixady.paradox.user.model.UserModel;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    private final JwtProperties jwtProperties;
    private final JwtKeyProvider jwtKeyProvider;

    public String createAccessToken(UserModel userModel) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtProperties.getAccessTokenExpirationMs());

        return Jwts.builder()
                .setSubject(userModel.getId().toString())
                .claim("roles", userModel.getRoles().stream()
                        .map(GrantedAuthority::getAuthority)
                        .toList())
                .claim("type",TokenType.ACCESS.name())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(jwtKeyProvider.getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String createRefreshToken(UserModel userModel) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtProperties.getRefreshTokenExpirationMs());

        return Jwts.builder()
                .setSubject(userModel.getId().toString())
                .claim("type",TokenType.REFRESH.name())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(jwtKeyProvider.getKey(), SignatureAlgorithm.HS256)
                .compact();
    }
}
