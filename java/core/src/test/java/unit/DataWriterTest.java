package unit;

import core.io.DataWriter;
import core.io.Util;
import core.io.Writer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class DataWriterTest {

    @Test
    @DisplayName("Given file test if buffer was flushed properly")
    public void testDataWriterBehaviour() throws IOException {
        var file = Util.createTempFile("txt");
        Writer writer = DataWriter.of(file);
        Writer spy = Mockito.spy(writer);
        spy.append("Hello").append("World");
        verify(spy, times(2)).flush();
        assertNotNull(file);
        Util.removeTempFile(file.getPath());
    }
}
