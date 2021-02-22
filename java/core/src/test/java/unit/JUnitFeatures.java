package unit;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class JUnitFeatures {

    private int count = 0;
    private String init;

    @BeforeEach
    public void setupBeforeEach() {
        count += 1;
    }

    @BeforeAll
    public void setupBeforeAll() {
        init = "Hello World";
    }

    @ParameterizedTest
    @ValueSource(strings = {"hello", "world"})
    public void dummy(String var1) {
        assertTrue(var1.length() > 0);
    }

    @ParameterizedTest
    @CsvSource({"apple,1", "banana,2"})
    public void csv(String fruit, int rank) {
        assertTrue(fruit.length() > 0);
        assertTrue(rank >= 1);
    }

    @Test
    public void assertCount() {
        assertEquals(5, count);
        assertEquals("Hello World", init);
    }
}
