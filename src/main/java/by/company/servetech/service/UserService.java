package by.company.servetech.service;

import by.company.servetech.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto editUser(UserDto dto);

    void deleteUserById(Integer id);

    List<UserDto> getUsers();

    void deleteUsersInRange(Integer idUserFrom, Integer idUserTo);
}
