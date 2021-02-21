package unit;

import core.Util;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class UtilTest {

    @Test
    @DisplayName("Test util class when manipulating temporary files")
    public void testTempFileManagement() throws IOException {
        var file = Util.createTempFile("txt");
        assertNotNull(file);
        assertTrue(file.exists());
        assertTrue(file.canWrite());
        assertTrue(file.canRead());
        Util.removeTempFile(file.getPath());
        assertFalse(file.exists());
    }
}
