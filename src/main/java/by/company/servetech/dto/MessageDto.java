package by.company.servetech.dto;

public class MessageDto {
	private UserDto user;
	private String action = "use request GET /users";

    public MessageDto() {
    }

    public MessageDto(UserDto user, String action) {
        this.user = user;
        this.action = action;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
