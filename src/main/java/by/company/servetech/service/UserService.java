package by.company.servetech.service;

import by.company.servetech.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto editUser(UserDto dto);

    UserDto createUser(UserDto dto);

    void deleteUserById(Integer id);

    List<UserDto> getUsers();

    UserDto getUserByLogin(String login);

    void deleteUsersInRange(Integer idUserFrom, Integer idUserTo);
}
