package by.company.servetech.service.impl;

import by.company.servetech.config.security.JwtProvider;
import by.company.servetech.dto.LoginRequest;
import by.company.servetech.dto.UserDto;
import by.company.servetech.service.AuthService;
import by.company.servetech.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Override
    public String authenticate(LoginRequest dto) {
        String login = dto.getLogin();
        String password = dto.getPassword();

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(login, password);
        Authentication authentication = authenticationManager.authenticate(authToken);
//        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return jwtProvider.generateToken(authentication);
    }

    @Override
    public UserDto registration(UserDto dto) {
        return userService.createUser(dto);
    }

    //TODO
    @Override
    public void logout() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println(principal.toString());
        System.out.println(name);
    }
}
