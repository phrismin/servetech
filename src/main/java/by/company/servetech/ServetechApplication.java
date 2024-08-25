package by.company.servetech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class ServetechApplication {

    private static String[] arguments;

    public static void main(String[] args) {
        arguments = args;
        SpringApplication.run(ServetechApplication.class, args);
    }

    public static void shutdown() {
        ConfigurableApplicationContext ctx = SpringApplication.run(ServetechApplication.class, arguments);
        int exitCode = SpringApplication.exit(ctx, () -> 0);
        System.exit(exitCode);
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
