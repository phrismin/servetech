package by.company.servetech.controller;

import by.company.servetech.dto.UserDto;
import by.company.servetech.model.User;
import by.company.servetech.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private SimpMessagingTemplate template;

    @PostMapping("/save")
    public ResponseEntity<User> saveUser(@RequestBody @Valid UserDto dto) {
        User user = userService.saveUser(dto);
        return ResponseEntity.ok(user);
    }

    //TODO что вернуть
    @DeleteMapping("/delete/{id}")
    public boolean deleteUserById(@PathVariable Integer id) {
        userService.deleteUserById(id);
        return true;
    }

    @PostMapping("/getUsers")
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = userService.getUsers();
        template.convertAndSend("/topic/users", users);
        return ResponseEntity.ok(users);
    }
}
