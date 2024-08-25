package by.company.servetech.service;

import by.company.servetech.dto.UserDto;
import by.company.servetech.model.User;

import java.util.List;

public interface UserService {

    UserDto editUser(UserDto dto);
    UserDto createUser(UserDto dto);
    boolean deleteUserById(Integer id);
    List<User> getUsers();

    UserDto getUserByLogin(String login);
}
