package RecursiveBacktracingTest;

import RecursiveBacktracing.PhoneNumberLetterCombinations;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Arrays;

public class PhoneNumberLetterCombinationsTest {
    private PhoneNumberLetterCombinations solution;

    @BeforeEach
    public void setUp() {
        solution = new PhoneNumberLetterCombinations();
    }

    // Edge Cases
    @Test
    public void testEmptyInput() {
        List<String> result = solution.generateLetterCombinations("");
        assertTrue(result.isEmpty(), "Empty input should return an empty list");
    }

    // Basic Scenarios
    @Test
    public void testSingleDigitInput() {
        List<String> result = solution.generateLetterCombinations("2");
        List<String> expected = Arrays.asList("a", "b", "c");
        assertEquals(expected, result, "Single digit input should return correct combinations");
    }

    @Test
    public void testTwoDigitsInput() {
        List<String> result = solution.generateLetterCombinations("23");
        List<String> expected = Arrays.asList(
                "ad", "ae", "af",
                "bd", "be", "bf",
                "cd", "ce", "cf"
        );
        assertEquals(expected, result, "Two digits input should return correct combinations");
    }

    // Complex Scenarios
    @Test
    public void testThreeDigitsInput() {
        List<String> result = solution.generateLetterCombinations("234");
        assertNotNull(result);
        assertFalse(result.isEmpty(), "Three digits input should generate combinations");
        // Let's Verify some expected combinations
        assertTrue(result.contains("adg"));
        assertTrue(result.contains("beh"));
        assertTrue(result.contains("cfi"));
    }

    // Validation Tests
    @Test
    public void testOutputConsistency() {
        List<String> result1 = solution.generateLetterCombinations("23");
        List<String> result2 = solution.generateLetterCombinations("23");
        assertEquals(result1, result2, "Multiple calls should produce consistent results");
    }

    @Test
    public void testOutputSorting() {
        List<String> result = solution.generateLetterCombinations("23");
        assertTrue(isLexicographicallySorted(result), "Output should be lexicographically sorted");
    }

    // Exception Tests
    @Test
    public void testNullInput() {
        assertThrows(IllegalArgumentException.class,
                () -> solution.generateLetterCombinations(null),
                "Null input should throw IllegalArgumentException");
    }

    @Test
    public void testInvalidDigitsBelowRange() {
        assertThrows(IllegalArgumentException.class,
                () -> solution.generateLetterCombinations("1234"),
                "Digits below 2 should throw IllegalArgumentException");
    }

    @Test
    public void testInvalidDigitsAboveRange() {
        assertThrows(IllegalArgumentException.class,
                () -> solution.generateLetterCombinations("0234"),
                "Digits above 9 should throw IllegalArgumentException");
    }

    @Test
    public void testExceedMaximumLength() {
        assertThrows(IllegalArgumentException.class,
                () -> solution.generateLetterCombinations("123456"),
                "Input exceeding 4 digits should throw IllegalArgumentException");
    }

    // Helper method to check lexicographic sorting
    private boolean isLexicographicallySorted(List<String> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i).compareTo(list.get(i + 1)) > 0) {
                return false;
            }
        }
        return true;
    }
}