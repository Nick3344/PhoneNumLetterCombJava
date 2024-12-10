package DynamicProgApproachTest;

import DynamicProgApproach.PhoneNumberLetterCombinations;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Arrays;

public class PhoneNumberLetterCombinationsTest {
    private PhoneNumberLetterCombinations letterComposer;

    @BeforeEach
    public void setUp() {
        letterComposer = new PhoneNumberLetterCombinations();
    }

    @Test
    public void testEmptyInputScenario() {
        List<String> result = letterComposer.generateLetterCombinations("");
        assertTrue(result.isEmpty(), "Empty input should produce an empty result set");
    }

    @Test
    public void testSingleDigitCombinations() {
        List<String> result = letterComposer.generateLetterCombinations("2");
        List<String> expected = Arrays.asList("a", "b", "c");
        assertEquals(expected, result, "Single digit should generate correct letter combinations");
    }

    @Test
    public void testTwoDigitCombinations() {
        List<String> result = letterComposer.generateLetterCombinations("23");
        List<String> expected = Arrays.asList(
                "ad", "ae", "af",
                "bd", "be", "bf",
                "cd", "ce", "cf"
        );
        assertEquals(expected, result, "Two digits should generate all possible letter combinations");
    }

    @Test
    public void testThreeDigitCombinations() {
        List<String> result = letterComposer.generateLetterCombinations("456");
        assertNotNull(result);
        assertFalse(result.isEmpty(), "Three digits should generate multiple combinations");
        assertTrue(result.contains("gjm"), "Should contain sample three-digit combination");
    }

    @Test
    public void testResultSorting() {
        List<String> result = letterComposer.generateLetterCombinations("23");
        assertTrue(isSorted(result), "Combinations should be lexicographically sorted");
    }

    @Test
    public void testConsistentResultGeneration() {
        List<String> firstResult = letterComposer.generateLetterCombinations("23");
        List<String> secondResult = letterComposer.generateLetterCombinations("23");
        assertEquals(firstResult, secondResult, "Multiple invocations should yield consistent results");
    }

    @Test
    public void testNullInputHandling() {
        assertThrows(IllegalArgumentException.class,
                () -> letterComposer.generateLetterCombinations(null),
                "Null input should throw IllegalArgumentException");
    }

    @Test
    public void testInvalidDigitInput() {
        assertThrows(IllegalArgumentException.class,
                () -> letterComposer.generateLetterCombinations("1234"),
                "Input with invalid digits should throw IllegalArgumentException");
    }

    @Test
    public void testExcessiveLengthInput() {
        assertThrows(IllegalArgumentException.class,
                () -> letterComposer.generateLetterCombinations("123456"),
                "Input exceeding maximum length should throw IllegalArgumentException");
    }

    // Helper method to check if list is sorted
    private boolean isSorted(List<String> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i).compareTo(list.get(i + 1)) > 0) {
                return false;
            }
        }
        return true;
    }
}
