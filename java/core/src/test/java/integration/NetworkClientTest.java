package integration;

import core.ip.Client;
import core.network.NetworkClient;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class NetworkClientTest {

    private static Client client;

    @BeforeAll
    public static void setup() {
        client = new NetworkClient();
    }

    @Test
    @DisplayName("Test HttpClient doing a request to Google")
    public void testGoogleConnection() throws IOException, InterruptedException {
        assertNotNull(client);
        client.get("http://google.com");
        assertEquals(200, client.status());
    }

}
