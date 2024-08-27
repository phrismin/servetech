package by.company.servetech.controller;

import by.company.servetech.dto.UserDto;
import by.company.servetech.service.MessageService;
import by.company.servetech.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private MessageService messageService;

    @PostMapping("/edit")
    public ResponseEntity<UserDto> editUser(@RequestBody @Valid UserDto dto) {
        UserDto userDto = userService.editUser(dto);
        return ResponseEntity.ok(userDto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable Integer id) {
        userService.deleteUserById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getUsers")
    public ResponseEntity<List<UserDto>> getUsers() {
        List<UserDto> userDtoList = userService.getUsers();
        userDtoList.forEach(dto -> messageService.sendUserAction(dto));
        return ResponseEntity.ok(userDtoList);
    }

    @DeleteMapping("/deleteUsers")
    public ResponseEntity<?> deleteUsersInRange(@RequestParam Integer idUserFrom,
                                                @RequestParam Integer idUserTo) {
        userService.deleteUsersInRange(idUserFrom, idUserTo);
        return ResponseEntity.ok().build();
    }
}