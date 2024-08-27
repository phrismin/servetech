package by.company.servetech.service.impl;

import by.company.servetech.config.security.JwtProvider;
import by.company.servetech.dto.LoginRequestDto;
import by.company.servetech.dto.UserDto;
import by.company.servetech.exceptions.InvalidArgumentException;
import by.company.servetech.exceptions.UserNotFoundException;
import by.company.servetech.entity.Token;
import by.company.servetech.entity.User;
import by.company.servetech.repository.TokenRepository;
import by.company.servetech.repository.UserRepository;
import by.company.servetech.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String authenticate(LoginRequestDto dto) {
        String login = dto.getLogin();
        String password = dto.getPassword();

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(login, password);
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user = userRepository.findByLogin(login)
                .orElseThrow(() -> new UserNotFoundException(login));

        String token = jwtProvider.generateToken(user.getLogin());
        revokeAllToken(user);
        saveToken(user);
        return token;
    }

    @Override
    public UserDto registration(UserDto dto) {
        if (userRepository.existsByLogin(dto.getLogin())) {
            throw new InvalidArgumentException(dto.getLogin());
        }

        User user = new User();
        user.setLogin(dto.getLogin());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setFullName(dto.getFullName());
        user.setGender(dto.getGender());
        User saveUser = userRepository.save(user);

        saveToken(saveUser);

        return new UserDto(
                saveUser.getId(),
                saveUser.getLogin(),
                null,
                saveUser.getFullName(),
                saveUser.getGender());
    }

    private void revokeAllToken(User user) {
        List<Token> tokens = tokenRepository.findAllValidTokensByUser(user.getId());
        if (tokens.isEmpty()) {
            return;
        }
        tokens.forEach(token -> token.setRevoked(true));
        tokenRepository.saveAll(tokens);
    }

    private void saveToken(User user) {
        Token token = new Token();
        token.setUser(user);
        token.setToken(jwtProvider.generateToken(user.getLogin()));
        token.setRevoked(false);
        tokenRepository.save(token);
    }
}