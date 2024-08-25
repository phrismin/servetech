package by.company.servetech.controller;

import by.company.servetech.dto.UserDto;
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
    public ResponseEntity<?> deleteUserById(@PathVariable Integer id) {
        userService.deleteUserById(id);
        return ResponseEntity.ok().build();
    }

    //список пользователей
//    @MessageMapping("/topic.getUsers")
//    @SendTo("/topic/public")
    @GetMapping("/getUsers")
    public ResponseEntity<List<UserDto>> getUsers() {
        List<UserDto> userDtoList = userService.getUsers();
//        template.convertAndSend("/topic/users", users);
        return ResponseEntity.ok(userDtoList);
    }

    @DeleteMapping("/deleteUsers")
    public ResponseEntity<?> deleteUsersInRange(@RequestParam Integer idUserFrom,
                                                @RequestParam Integer idUserTo) {
        userService.deleteUsersInRange(idUserFrom, idUserTo);
        return ResponseEntity.ok().build();
    }
}