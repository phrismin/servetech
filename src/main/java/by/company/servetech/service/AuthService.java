package by.company.servetech.service;

import by.company.servetech.dto.LoginRequestDto;
import by.company.servetech.dto.UserDto;

public interface AuthService {

    String authenticate(LoginRequestDto dto);
    UserDto registration(UserDto dto);
}
