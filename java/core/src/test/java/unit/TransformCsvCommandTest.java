package unit;

import core.command.TransformCsvCommand;
import core.service.MergeColumnService;
import core.service.ReadCsvService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TransformCsvCommandTest {

    @Mock private MergeColumnService mergeColumnService;

    @Mock private ReadCsvService readCsvService;

    @Test
    @DisplayName("Test to assert null when file not found when file is read")
    public void testFileNotFoundInTransformCall() throws IOException {
        var transformCsvCommand =
                new TransformCsvCommand(readCsvService, mergeColumnService, "data.csv");
        when(readCsvService.readBin(eq("data.csv"))).thenThrow(new FileNotFoundException());
        var result = transformCsvCommand.execute();

        verify(readCsvService, times(1)).readBin(eq("data.csv"));
        verify(mergeColumnService, times(0)).mergeColumn(anyString());

        assertNull(result);
    }

    @Test
    @DisplayName("Test to check if result was get from merge")
    public void testReturnStructureFromTransform() throws IOException {
        var transformCsvCommand =
                new TransformCsvCommand(readCsvService, mergeColumnService, "data.csv");

        Map<String, String> expected = new HashMap<>();
        expected.put("A", "B");
        expected.put("C", "D");
        expected.put("D", "K");
        expected.put("I", "A");
        expected.put("L", "A");

        when(readCsvService.readBin(eq("data.csv"))).thenReturn(this.getCsvStringTest());
        when(mergeColumnService.mergeColumn(this.getCsvStringTest())).thenReturn(expected);

        var result = transformCsvCommand.execute();

        verify(readCsvService, times(1)).readBin(eq("data.csv"));
        verify(mergeColumnService, times(1)).mergeColumn(this.getCsvStringTest());

        assertEquals(result, expected);
    }

    private String getCsvStringTest() {
        return "col1;col2\n" + "A;B\n" + "C;D\n" + "A;U\n" + "I;A\n" + "D;K\n" + "C;L\n" + "L;A";
    }
}
