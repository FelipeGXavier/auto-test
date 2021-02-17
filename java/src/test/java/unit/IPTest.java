package unit;

import core.ip.IP;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class IPTest {


    @Test
    @DisplayName("Test valid IP address instance")
    public void testValidIpAddress() {
        var address = "172.217.28.142";
        IP ip = IP.of(address);
        assertEquals(ip.getAddress(), address);
    }

    @Test
    @DisplayName("Test invalid IP address instance")
    public void testInvalidIpAddress() {
        var address = "999.999.999.99";
        var exception = assertThrows(RuntimeException.class, () -> IP.of(address));
        assertEquals(exception.getMessage(), "Invalid address");
    }

    @Test
    @DisplayName("Test null IP address instance")
    public void testNullIpAddress() {
        var exception = assertThrows(RuntimeException.class, () -> IP.of(null));
        assertEquals(exception.getMessage(), "Invalid address");
    }

}
