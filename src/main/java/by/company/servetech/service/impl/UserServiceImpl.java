package by.company.servetech.service.impl;

import by.company.servetech.dto.UserDto;
import by.company.servetech.model.User;
import by.company.servetech.repository.UserRepository;
import by.company.servetech.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

//    @Autowired
//    private StompService stompService;

    //TODO converter, через абстрактный класс
    public UserDto createUser(UserDto dto) {
        if (userRepository.existsByLogin(dto.getLogin())) {
            throw new RuntimeException("User with this login already exists");
        }
        User user = new User();
        user.setLogin(dto.getLogin());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setFullName(dto.getFullName());
        user.setGender(dto.getGender());
        User saveUser = userRepository.save(user);
        return new UserDto(saveUser.getLogin(), null, saveUser.getFullName(), saveUser.getGender());
    }

    @Override
    public User saveUser(UserDto dto) {
        return null;
    }


    public boolean deleteUserById(Integer id) {
        userRepository.deleteById(id);
        return true;
    }

    public List<User> getUsers() {
        //TODO асинхронно сделать рассылку
//        stompService.mail();
        return userRepository.findAll();
    }

    @Override
    public User getUserByLogin(String login) {
        User user = userRepository.findByLogin(login).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return user;
    }

    @Override
    public UserDto registration(UserDto dto) {
        User user = getUserByLogin(dto.getLogin());


        createUser(dto);
        return null;
    }
}
