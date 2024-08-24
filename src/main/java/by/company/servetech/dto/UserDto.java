package by.company.servetech.dto;

import by.company.servetech.model.Gender;

public class UserDto {

    private String login;

    private String password;

    private String fullName;

    private Gender gender;

    public UserDto(String login, String password, String fullName, Gender gender) {
        this.login = login;
        this.password = password;
        this.fullName = fullName;
        this.gender = gender;
    }

    public UserDto() {
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

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
