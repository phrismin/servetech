package by.company.servetech.controller;

import by.company.servetech.dto.JwtResponseDto;
import by.company.servetech.dto.LoginDto;
import by.company.servetech.dto.UserDto;
import by.company.servetech.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    //вход в систему под созданным пользователем
    @PostMapping("/login")
    public ResponseEntity<JwtResponseDto> authenticate(@Valid @RequestBody LoginDto loginDto) {
        String jwtToken = authService.authenticate(loginDto);
        return ResponseEntity.ok(new JwtResponseDto().token(jwtToken));
    }

    //создание пользователя
    @PostMapping("/signin")
    public ResponseEntity<UserDto> registration(@RequestBody @Valid UserDto dto) {
        UserDto result = authService.registration(dto);
        return ResponseEntity.ok(result);
    }
}