package by.company.servetech;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ClientTest {

    public static void main(String[] args) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();

        String body = """
                            {
                            "login":"alexx",
                            "password":"p/32432csvd",
                            "fullName": "Test van Test",
                            "gender": "MALE"
                            }
                            """;
        HttpRequest requestSignin = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:9000/api/auth/signin"))
                .header("Content-type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();
        HttpResponse<String> response = client.send(requestSignin, HttpResponse.BodyHandlers.ofString());
        System.out.println(response);

        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ4eHgiLCJpYXQiOjE3MjQ3NzgyMDAsImV4cCI6MTcyNDc4MTIwMH0.3j4fhaVIalSxWp392pWaW5ZjiVCw1UbjXVJdQxLNEvc";
        HttpRequest requestLogin = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:9000/api/auth/login"))
                .header("Content-type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .header("Authorization", token)
                .build();
        response = client.send(requestLogin, HttpResponse.BodyHandlers.ofString());
        System.out.println(response);
    }
}
