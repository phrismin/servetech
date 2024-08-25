package by.company.servetech.service;


import by.company.servetech.dto.LoginRequest;
import by.company.servetech.dto.UserDto;

public interface AuthService {

    String authenticate(LoginRequest dto);
    UserDto registration(UserDto dto);
}
