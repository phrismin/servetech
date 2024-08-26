package by.company.servetech.service.impl;

import by.company.servetech.dto.UserDto;
import by.company.servetech.model.Token;
import by.company.servetech.model.User;
import by.company.servetech.repository.TokenRepository;
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
    private TokenRepository tokenRepository;

    @Autowired
    private StompService stompService;


    @Override
    public UserDto editUser(UserDto dto) {
        User user = userRepository.findById(dto.getId())
                .orElseThrow(() -> new IllegalArgumentException("User with this login already exists"));
        User saveUser = saveUser(user, dto);
        return new UserDto(
                saveUser.getId(),
                saveUser.getLogin(),
                null,
                saveUser.getFullName(),
                saveUser.getGender());
    }

    @Override
    public UserDto createUser(UserDto dto) {
        userRepository.findById(dto.getId())
                .orElseThrow(() -> new IllegalArgumentException("User with this login already exists"));
        User saveUser = saveUser(new User(), dto);
        saveToken(saveUser);
        return new UserDto(
                saveUser.getId(),
                saveUser.getLogin(),
                null,
                saveUser.getFullName(),
                saveUser.getGender());
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

    private User saveUser(User user, UserDto dto) {
        user.setLogin(dto.getLogin());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setFullName(dto.getFullName());
        user.setGender(dto.getGender());
        return userRepository.save(user);
    }

    private Token saveToken(User user) {
        Token token = new Token();
        token.setUser(user);
        token.setExpired(false);
        token.setRevoked(false);
        return tokenRepository.save(token);
    }
}
