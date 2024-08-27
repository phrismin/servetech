package by.company.servetech.dto;

public class JwtResponseDto {
    private String token;

    public JwtResponseDto token(String token) {
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