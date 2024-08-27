package by.company.servetech.dto;

public class LoginResponseDto {
    private String token;

    public LoginResponseDto token(String token) {
        this.token = token;
        return this;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}