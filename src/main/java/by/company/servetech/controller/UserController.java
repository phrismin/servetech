package by.company.servetech.controller;

import by.company.servetech.dto.UserDto;
import by.company.servetech.model.User;
import by.company.servetech.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private SimpMessagingTemplate template;

    //редактирование пользователя
    @PostMapping("/edit")
    public ResponseEntity<UserDto> editUser(@RequestBody @Valid UserDto dto) {
        UserDto userDto = userService.editUser(dto);
        return ResponseEntity.ok(userDto);
    }

    //TODO что вернуть
    //удаление пользователя
    @DeleteMapping("/delete/{id}")
    public boolean deleteUserById(@PathVariable Integer id) {
        userService.deleteUserById(id);
        return true;
    }

    //список пользователей
    @PostMapping("/getUsers")
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = userService.getUsers();
        template.convertAndSend("/topic/users", users);
        return ResponseEntity.ok(users);
    }
}
