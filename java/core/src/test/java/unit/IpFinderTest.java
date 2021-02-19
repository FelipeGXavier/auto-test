package unit;

import core.ip.Client;
import core.ip.IP;
import core.ip.IpFinder;
import core.ip.IpResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class IpFinderTest {

    @Mock
    private Client networkClient;
    @Mock
    private IP ip;

    @Test
    @DisplayName("Test response given valid response payload")
    public void testValidValidationIpFinder() throws IOException, InterruptedException {
        var ipFinder = new IpFinder(this.networkClient);
        // Set mocks
        when(this.networkClient.get(anyString())).thenReturn(this.validResponsePayload());
        when(this.networkClient.status()).thenReturn(200);
        Optional<IpResponse> response = ipFinder.find(this.ip);
        // Check if status was executed
        verify(this.networkClient, times(1)).status();
        // Verify execution order
        InOrder inOrder = Mockito.inOrder(this.networkClient);
        inOrder.verify(this.networkClient).get(anyString());
        inOrder.verify(this.networkClient).status();
        // Assertions
        assertTrue(response.isPresent());
        var ipResponse = response.get();
        assertEquals("São Paulo", ipResponse.getCity());
        assertEquals("South America", ipResponse.getContinent());
        assertEquals("Brazil", ipResponse.getCountry());
        assertEquals("-23.5505", ipResponse.getLatitude());
        assertEquals("-46.6333", ipResponse.getLongitude());
    }

    @Test
    @DisplayName("Test response given invalid status code")
    public void testInvalidStatusCode() throws IOException, InterruptedException {
        var ipFinder = new IpFinder(this.networkClient);
        when(this.networkClient.get(anyString())).thenReturn(this.validResponsePayload());
        when(this.networkClient.status()).thenReturn(500);
        Optional<IpResponse> response = ipFinder.find(this.ip);
        assertFalse(response.isPresent());
    }

    @Test
    @DisplayName("Test response given invalid payload response")
    public void testMismatchResponse() throws IOException, InterruptedException {
        var ipFinder = new IpFinder(this.networkClient);
        when(this.networkClient.get(anyString())).thenReturn(this.mismatchPayload());
        when(this.networkClient.status()).thenReturn(200);
        Optional<IpResponse> response = ipFinder.find(this.ip);
        assertFalse(response.isPresent());
    }

    private String mismatchPayload() {
        return "{\n"
                + "  \"status\": \"success\",\n"
                + "  \"continentCode\": \"SA\",\n"
                + "  \"country\": \"Brazil\",\n"
                + "  \"countryCode\": \"BR\",\n"
                + "  \"region\": \"SP\",\n"
                + "  \"regionName\": \"Sao Paulo\",\n"
                + "  \"city\": \"São Paulo\",\n"
                + "  \"district\": \"\",\n"
                + "  \"zip\": \"01000-000\",\n"
                + "  \"lat\": -23.5505,\n"
                + "  \"lon\": -46.6333,\n"
                + "  \"timezone\": \"America/Sao_Paulo\",\n"
                + "  \"offset\": -10800,\n"
                + "  \"currency\": \"BRL\",\n"
                + "  \"isp\": \"Google LLC\",\n"
                + "  \"org\": \"Google LLC\",\n"
                + "  \"as\": \"AS15169 Google LLC\",\n"
                + "  \"asname\": \"GOOGLE\",\n"
                + "  \"mobile\": false,\n"
                + "  \"proxy\": false,\n"
                + "  \"hosting\": true,\n"
                + "  \"query\": \"172.217.28.142\"\n"
                + "}";
    }

    private String validResponsePayload() {
        return "{\n"
                + "  \"status\": \"success\",\n"
                + "  \"continent\": \"South America\",\n"
                + "  \"continentCode\": \"SA\",\n"
                + "  \"country\": \"Brazil\",\n"
                + "  \"countryCode\": \"BR\",\n"
                + "  \"region\": \"SP\",\n"
                + "  \"regionName\": \"Sao Paulo\",\n"
                + "  \"city\": \"São Paulo\",\n"
                + "  \"district\": \"\",\n"
                + "  \"zip\": \"01000-000\",\n"
                + "  \"lat\": -23.5505,\n"
                + "  \"lon\": -46.6333,\n"
                + "  \"timezone\": \"America/Sao_Paulo\",\n"
                + "  \"offset\": -10800,\n"
                + "  \"currency\": \"BRL\",\n"
                + "  \"isp\": \"Google LLC\",\n"
                + "  \"org\": \"Google LLC\",\n"
                + "  \"as\": \"AS15169 Google LLC\",\n"
                + "  \"asname\": \"GOOGLE\",\n"
                + "  \"mobile\": false,\n"
                + "  \"proxy\": false,\n"
                + "  \"hosting\": true,\n"
                + "  \"query\": \"172.217.28.142\"\n"
                + "}";
    }
}
