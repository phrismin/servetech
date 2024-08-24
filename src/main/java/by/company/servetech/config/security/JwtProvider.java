package by.company.servetech.config.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtProvider {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private Integer jwtExpiration;

    public String generateToken(Authentication authentication) {
        return Jwts.builder()
                .subject(authentication.getName())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .signWith(getSignKey())
                .compact();
    }

    public String extractLogin(String jwtToken) {
        return Jwts
                .parser()
                .verifyWith(getSignKey())
                .build()
                .parseSignedClaims(jwtToken)
                .getPayload()
                .getSubject();
    }

    private <T> T extractClaim(String jwtToken, Function<Claims, T> claimsResolvers) {
        Claims claims = extractAllClaims(jwtToken);
        return claimsResolvers.apply(claims);
    }

    private Claims extractAllClaims(String jwtToken) {
        return Jwts
                .parser()
                .verifyWith(getSignKey())
                .build()
                .parseSignedClaims(jwtToken)
                .getPayload();
    }

    public boolean isValidJwtToken(String jwtToken, UserDetails userDetails) {
        String login = extractLogin(jwtToken);
        return login.equals(userDetails.getUsername()) && isJwtTokenExpired(jwtToken);
    }

    private SecretKey getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private boolean isJwtTokenExpired(String jwtToken) {
        return extractClaim(jwtToken, Claims::getExpiration).after(new Date());
    }
}

