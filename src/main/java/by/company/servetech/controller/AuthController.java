package by.company.servetech.controller;

import by.company.servetech.dto.JwtResponse;
import by.company.servetech.dto.LoginRequest;
import by.company.servetech.dto.UserDto;
import by.company.servetech.model.User;
import by.company.servetech.service.AuthService;
import by.company.servetech.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    //вход в систему под созданным пользователем
    @PostMapping("/login")
    public ResponseEntity<JwtResponse> authenticate(@Valid @RequestBody LoginRequest loginRequest) {
        String jwtToken = authService.authenticate(loginRequest);
        return ResponseEntity.ok(new JwtResponse().token(jwtToken));
    }

    //создание пользователя
    @PostMapping("/signin")
    public ResponseEntity<UserDto> registration(@RequestBody UserDto dto) {
        UserDto result = authService.registration(dto);
        return ResponseEntity.ok(result);
    }


    //TODO
    //выход из системы
    @PostMapping("/exit")
    public ResponseEntity<?> exit() {
        return null;
    }

    @GetMapping("/test")
    public String test() {
        return "";
    }
}