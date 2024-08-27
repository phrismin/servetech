package by.company.servetech.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class LoginRequestDto {
    @NotEmpty(message = "Login cannot empty")
    @Size(max = 50, message = "Login cannot be more 50 symbols")
    private String login;

    @Pattern(regexp = "(\\?:.*\\d.*\\d.*\\d.*[^0-9\\s].*|.*[^0-9\\s].*\\d.*\\d.*\\d.*)",
            message = "Password must contain special characters and 3 numbers")
    @Size(min = 7,  message = "Password cannot be less 7 symbols")
    private String password;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
