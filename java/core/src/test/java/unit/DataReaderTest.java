package unit;

import core.Util;
import core.io.DataReader;
import core.io.Reader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class DataReaderTest {

    @Test
    @DisplayName("Test empty file read")
    public void givenEmptyFileMustReturnEmptyString() throws IOException {
        Reader reader = new DataReader();
        var file = Util.createTempFile("txt");
        assertNotNull(file);
        var result = reader.read(file.getPath());
        Util.removeTempFile(file.getPath());
        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("Test file with data")
    public void givenFileWithDataMustReturnFileData() throws IOException {
        Reader reader = new DataReader();
        var file = Util.createTempFile("txt");
        assertNotNull(file);
        var writer = new FileWriter(file);
        writer.write("Hello World!");
        writer.close();
        var result = reader.read(file.getPath());
        Util.removeTempFile(file.getPath());
        assertEquals("Hello World!", result);
    }
}
