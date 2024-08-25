package by.company.servetech.service.impl;

import by.company.servetech.dto.UserDto;
import by.company.servetech.dto.MessageMail;
import by.company.servetech.service.StompService;
import by.company.servetech.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class StompServiceImpl implements StompService {

    @Autowired
    private SimpMessagingTemplate template;

    @Autowired
    @Lazy
    private UserService userService;

    @Override
    public void mail() {
        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        UserDto userDto = userService.getUserByLogin(login);
        template.convertAndSend("/topic/users", new MessageMail(userDto));
    }
}
