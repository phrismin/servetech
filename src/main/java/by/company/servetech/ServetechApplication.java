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
}
