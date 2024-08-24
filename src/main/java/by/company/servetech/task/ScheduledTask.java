package by.company.servetech.task;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@EnableScheduling
public class ScheduledTask implements ApplicationContextAware {

    @Value("${server.stopTime}")
    private String stopTime;

    private ApplicationContext context;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    @Scheduled(cron = "0 */10 * * * *")
    public void checkStopServerTime() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime dateTimeStop = LocalDateTime.parse(stopTime, formatter);

        if (now.isAfter(dateTimeStop)) {
            ((ConfigurableApplicationContext) context).close();
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        this.context = context;
    }
}