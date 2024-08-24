package by.company.servetech.dto;

import by.company.servetech.model.User;

public class MessageMail {
    private User user;
    private static final String action = "use request GET /users";

    public MessageMail(User user) {
        this.user = user;
    }
}