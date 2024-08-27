package by.company.servetech.service;

import by.company.servetech.dto.LoginDto;
import by.company.servetech.dto.UserDto;

public interface AuthService {

    String authenticate(LoginDto dto);
    UserDto registration(UserDto dto);
}
