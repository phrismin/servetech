package by.company.servetech.service.impl;

import by.company.servetech.dto.UserDto;
import by.company.servetech.model.Status;
import by.company.servetech.model.User;
import by.company.servetech.repository.UserRepository;
import by.company.servetech.service.StompService;
import by.company.servetech.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

    @Autowired
    private StompService stompService;

    //TODO converter, через абстрактный класс
    public UserDto createUser(UserDto dto) {
        if (userRepository.existsByLogin(dto.getLogin())) {
            throw new RuntimeException("User with this login already exists");
        }
        User user = new User();
        user.setStatus(Status.ACTIVE);
        return save(user, dto);
    }

    @Override
    public UserDto editUser(UserDto dto) {
        User user = userRepository.findById(dto.getId())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return save(user, dto);
    }

    public void deleteUserById(Integer id) {
        userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        userRepository.deleteById(id);
    }

    public List<UserDto> getUsers() {
        //TODO асинхронно сделать рассылку
//        stompService.mail();
        return userRepository.findAll()
                .stream()
                .map(user -> new UserDto(
                        user.getId(),
                        user.getLogin(),
                        null,
                        user.getFullName(),
                        user.getGender()))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto getUserByLogin(String login) {
        User user = userRepository.findByLogin(login).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new UserDto(user.getId(), user.getLogin(), null, user.getFullName(), user.getGender());
    }

    @Override
    public void deleteUsersInRange(Integer idUserFrom, Integer idUserTo) {
        userRepository.deleteUsersInRange(idUserFrom, idUserTo);
    }


    private UserDto save(User user, UserDto dto) {
        user.setLogin(dto.getLogin());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setFullName(dto.getFullName());
        user.setGender(dto.getGender());
        User saveUser = userRepository.save(user);
        return new UserDto(
                saveUser.getId(),
                saveUser.getLogin(),
                null,
                saveUser.getFullName(),
                saveUser.getGender());
    }
}
