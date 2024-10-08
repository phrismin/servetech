package by.company.servetech.dto;

import by.company.servetech.entity.Gender;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UserDto {

    private int id;

    @NotEmpty(message = "Login cannot empty")
    @Size(max = 50, message = "Login cannot be more 50 symbols")
    private String login;

    @Pattern(regexp = "(\\?:.*\\d.*\\d.*\\d.*[^0-9\\s].*|.*[^0-9\\s].*\\d.*\\d.*\\d.*)",
            message = "Password must contain special characters and 3 numbers")
    @Size(min = 7,  message = "Password cannot be less 7 symbols")
    @JsonIgnore
    private String password;

    @NotEmpty(message = "FullName cannot empty")
    @Size(max = 256, message = "FullName cannot be more 256 symbols")
    private String fullName;

    @NotNull(message = "Gender cannot null")
    private Gender gender;

    public UserDto(int id, String login, String password, String fullName, Gender gender) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.fullName = fullName;
        this.gender = gender;
    }

    public UserDto(int id, String login, String fullName, Gender gender) {
        this.id = id;
        this.login = login;
        this.fullName = fullName;
        this.gender = gender;
    }

    public UserDto() {
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public String getFullName() {
        return fullName;
    }

    public Gender getGender() {
        return gender;
    }
}