package by.company.servetech.config.security;

import by.company.servetech.model.Token;
import by.company.servetech.repository.TokenRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class LogoutService implements LogoutHandler {

    @Autowired
    private TokenRepository tokenRepository;

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        String bearerToken = request.getHeader("Authorization");
        if (!StringUtils.hasText(bearerToken) && !bearerToken.startsWith("Bearer ")) {
            return;
        }
        String jwtToken = bearerToken.substring(7);
        Token storedToken = tokenRepository.findByToken(jwtToken).orElse(null);
        if (storedToken != null) {
            storedToken.setRevoked(true);
            storedToken.setExpired(true);
            tokenRepository.save(storedToken);
        }
    }
}
