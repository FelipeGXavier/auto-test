package core.network;

import core.contracts.Client;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class NetworkClient implements Client {

    private final HttpClient client = HttpClient.newBuilder().followRedirects(HttpClient.Redirect.ALWAYS).build();
    private HttpResponse<?> response;

    public String get(String url) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(url)).build();
        this.response = this.client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body().toString();
    }

    public int status() {
        return this.response.statusCode();
    }
}
