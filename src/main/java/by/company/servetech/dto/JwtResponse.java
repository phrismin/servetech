package by.company.servetech.dto;

public class JwtResponse {
    private String token;
    private String type = "Bearer ";

    public JwtResponse token(String token) {
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