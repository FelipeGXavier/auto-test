package integration;

import core.ip.Client;
import core.ip.IP;
import core.ip.IpFinder;
import core.network.NetworkClient;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IpFinderTest {

    private static Client client;

    @BeforeAll
    public static void setup() {
        client = new NetworkClient();
    }

    @DisplayName("Test request to get an IpResponse from internet")
    @Test
    public void testRequestIpData() throws IOException, InterruptedException {
        var finder = new IpFinder(client);
        var address = "172.217.28.142";
        var response = finder.find(IP.of(address));
        assertTrue(response.isPresent());
        var ipResponse = response.get();
        assertEquals("São Paulo", ipResponse.getCity());
        assertEquals("South America", ipResponse.getContinent());
        assertEquals("Brazil", ipResponse.getCountry());
        assertEquals("-23.5505", ipResponse.getLatitude());
        assertEquals("-46.6333", ipResponse.getLongitude());
    }
}
