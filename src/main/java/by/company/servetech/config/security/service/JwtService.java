package by.company.servetech.config.security.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {

    String extractLogin(String jwtToken);

    String generateToken(Authentication authentication);

    boolean isValidJwtToken(String jwtToken, UserDetails userDetails);
}

