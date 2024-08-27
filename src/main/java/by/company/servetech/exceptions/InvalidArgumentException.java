package by.company.servetech.exceptions;

public class InvalidArgumentException extends RuntimeException {

    public InvalidArgumentException(String login) {
        super("User with this " + login + " already exists");
    }
}
