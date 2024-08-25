package by.company.servetech.service.impl;

import by.company.servetech.config.security.JwtProvider;
import by.company.servetech.dto.LoginRequest;
import by.company.servetech.dto.UserDto;
import by.company.servetech.model.Token;
import by.company.servetech.model.User;
import by.company.servetech.repository.TokenRepository;
import by.company.servetech.repository.UserRepository;
import by.company.servetech.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
    public String authenticate(LoginRequest dto) {
        String login = dto.getLogin();
        String password = dto.getPassword();

       authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login, password));
        User user = userRepository.findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        String token = jwtProvider.generateToken(user.getLogin());
        saveUserToken(user);
        return token;
    }

    @Override
    public UserDto registration(UserDto dto) {
        if (userRepository.existsByLogin(dto.getLogin())) {
            throw new IllegalArgumentException("User with this login already exists");
        }
        User user = new User();
        user.setLogin(dto.getLogin());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setFullName(dto.getFullName());
        user.setGender(dto.getGender());
        User saveUser = userRepository.save(user);

        saveUserToken(saveUser);

        return new UserDto(
                saveUser.getId(),
                saveUser.getLogin(),
                null,
                saveUser.getFullName(),
                saveUser.getGender());
    }


    //TODO
    @Override
    public void logout() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println(principal.toString());
        System.out.println(name);
    }

    private void saveUserToken(User user) {
        Token token = new Token();
        token.setUser(user);
        token.setToken(jwtProvider.generateToken(user.getLogin()));
        token.setExpired(false);
        token.setRevoked(false);
        tokenRepository.save(token);
    }
}
