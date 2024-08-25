package by.company.servetech.dto;

import by.company.servetech.model.Gender;

public class UserDto {

    private int id;

    private String login;

    private String password;

    private String fullName;

    private Gender gender;

    public UserDto(int id, String login, String password, String fullName, Gender gender) {
        this.id = id;
        this.login = login;
        this.password = password;
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