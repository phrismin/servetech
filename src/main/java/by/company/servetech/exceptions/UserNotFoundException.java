package by.company.servetech.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Integer userId) {
        super("User not found with ID: " + userId);
    }

    public UserNotFoundException(String login) {
        super("User not found with login: " + login);
    }
}
