package by.company.servetech.service;


import by.company.servetech.dto.LoginRequest;

public interface AuthService {

    String authenticate(LoginRequest dto);
}
