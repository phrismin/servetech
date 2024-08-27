package by.company.servetech;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ClientTest {

    public static void main(String[] args) throws IOException, InterruptedException {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhbGVjeCIsImlhdCI6MTcyNDY2MjIyMywiZXhwIjoxNzI0NjY1MjIzfQ.WGoW7KKCA_JF9gRfTrYbOzmyVdA6UfqsRFO60dTvhPU";
        String body = """
                            {
                            "login":"alexx",
                            "password":"p/32432csvd",
                            "fullName": "Alex van alec",
                            "gender": "MALE"
                            }
                            """;
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:9000/api/user/create"))
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .header("Authorization", token)
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response);
    }
}
