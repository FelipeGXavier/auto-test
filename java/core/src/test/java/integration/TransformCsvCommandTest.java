package integration;

import core.command.TransformCsvCommand;
import core.io.DataReader;
import core.io.Reader;
import core.service.MergeColumnService;
import core.service.ReadCsvService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TransformCsvCommandTest {

    @Test
    @DisplayName("Test TransformCsvCommand with csv file to merge to unique key value structure")
    public void testParseCsv() {
        Reader reader = new DataReader();
        var mergeColumn = new MergeColumnService();
        var readCsv = new ReadCsvService(reader);
        var command = new TransformCsvCommand(readCsv, mergeColumn, "data.csv");
        var result = command.execute();
        Map<String, String> expected = new HashMap<>();
        expected.put("A", "B");
        expected.put("C", "D");
        expected.put("D", "K");
        expected.put("I", "A");
        expected.put("L", "A");
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Test to parse nonexistent file")
    public void testFileNotFoundTransformation() {
        Reader reader = new DataReader();
        var mergeColumn = new MergeColumnService();
        var readCsv = new ReadCsvService(reader);
        var command = new TransformCsvCommand(readCsv, mergeColumn, "sample.csv");
        var result = command.execute();
        assertNull(result);
    }

    @Test
    @DisplayName("Test to parse file with unformatted structure")
    public void testWrongDataset() {
        Reader reader = new DataReader();
        var mergeColumn = new MergeColumnService();
        var readCsv = new ReadCsvService(reader);
        var command = new TransformCsvCommand(readCsv, mergeColumn, "none.csv");
        Map<String, String> expected = new HashMap<>();
        expected.put("C", "D");
        expected.put("I", "A");
        expected.put("L", "A");
        assertEquals(expected, command.execute());
    }
}
