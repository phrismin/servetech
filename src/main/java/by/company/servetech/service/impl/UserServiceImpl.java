package by.company.servetech.service.impl;

import by.company.servetech.dto.UserDto;
import by.company.servetech.exceptions.InvalidArgumentException;
import by.company.servetech.exceptions.UserNotFoundException;
import by.company.servetech.entity.User;
import by.company.servetech.repository.UserRepository;
import by.company.servetech.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDto editUser(UserDto dto) {
        User user = userRepository.findById(dto.getId()).orElseThrow(() -> new InvalidArgumentException(dto.getLogin()));
        user.setLogin(dto.getLogin());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setFullName(dto.getFullName());
        user.setGender(dto.getGender());
        return new UserDto(
                user.getId(),
                user.getLogin(),
                user.getFullName(),
                user.getGender());
    }

    public void deleteUserById(Integer id) {
        userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        userRepository.deleteById(id);
    }

    public List<UserDto> getUsers() {
        return userRepository.findAll()
                .stream()
                .map(user -> new UserDto(
                        user.getId(),
                        user.getLogin(),
                        user.getFullName(),
                        user.getGender()))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteUsersInRange(Integer idUserFrom, Integer idUserTo) {
        userRepository.deleteUsersInRange(idUserFrom, idUserTo);
    }
}
