package by.company.servetech;

import by.company.servetech.dto.UserDto;
import by.company.servetech.model.Gender;
import by.company.servetech.model.User;
import by.company.servetech.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class ServetechApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServetechApplication.class, args);
    }

//    @Bean
//    public CommandLineRunner init(UserService userService) {
//        return args -> {
//            userService.createUser(new UserDto("alex", "p/32432csvd", "alex van valex", Gender.MALE));
//            userService.createUser(new UserDto("vasya", "64898*ashcb", "vasya vasya vasya", Gender.MALE));
//            userService.createUser(new UserDto("nobody", "vsund&4105", "nobody nobody nobody", Gender.UNDEFINED));
//            userService.createUser(new UserDto("olya", "876858scubautf$snuasch28", "olya olya olya", Gender.FEMALE));
//        };
//    }
}
