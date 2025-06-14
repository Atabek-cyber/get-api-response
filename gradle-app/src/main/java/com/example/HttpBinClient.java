package com.example;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class HttpBinClient {
    public static void main(String[] args) {
        try {
            // build the HTTP GET request
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://httpbin.org/json"))
                .GET()
                .build();

            // send and get the body as a string
            HttpResponse<String> response = client.send(request, 
                HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                System.err.println("Failed: HTTP " + response.statusCode());
                System.exit(1);
            }

            // parse JSON
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response.body());
            JsonNode titleNode = root.path("slideshow").path("title");

            if (titleNode.isMissingNode()) {
                System.err.println("Title not found in response");
                System.exit(2);
            }

            System.out.println(titleNode.asText());
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(3);
        }
    }
}
