package by.company.servetech.dto;

public class MessageMail {
    private UserDto userDto;
    private static final String action = "use request GET /users";

    public MessageMail(UserDto dto) {
        this.userDto = dto;
    }
}