package by.company.servetech.service.impl;

import by.company.servetech.dto.UserDto;
import by.company.servetech.service.MessageService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Override
    public void sendUserAction(UserDto user) {
        ObjectMapper objectMapper = new ObjectMapper();
        String json;
        try {
            json = objectMapper.writeValueAsString(user);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        simpMessagingTemplate.convertAndSend("/topic/userAction", json);
    }
}
