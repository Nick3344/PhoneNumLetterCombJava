package DynamicProgApproach;

import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class PhoneNumberLetterCombinations {
    // Digit-to-letter mapping
    private final Map<Character, char[]> telephoneDialLexicon = new HashMap<>() {{
        put('2', new char[]{'a', 'b', 'c'});
        put('3', new char[]{'d', 'e', 'f'});
        put('4', new char[]{'g', 'h', 'i'});
        put('5', new char[]{'j', 'k', 'l'});
        put('6', new char[]{'m', 'n', 'o'});
        put('7', new char[]{'p', 'q', 'r', 's'});
        put('8', new char[]{'t', 'u', 'v'});
        put('9', new char[]{'w', 'x', 'y', 'z'});
    }};

    private void validateTelephoneSequence(String phoneSequence) {
        if (phoneSequence == null) {
            throw new IllegalArgumentException("Telephone sequence cannot be null");
        }

        if (phoneSequence.length() > 4) {
            throw new IllegalArgumentException("Maximum telephone sequence length is 4 digits");
        }

        for (char digit : phoneSequence.toCharArray()) {
            if (!telephoneDialLexicon.containsKey(digit)) {
                throw new IllegalArgumentException("Invalid digit: Only digits 2-9 are permitted");
            }
        }
    }

    // Used the Dynamic Programming Approach
    public List<String> generateLetterCombinations(String telephoneSequence) {
        validateTelephoneSequence(telephoneSequence);

        // Let's handle empty input scenario
        if (telephoneSequence.isEmpty()) {
            return new ArrayList<>();
        }

        List<String> combinationAccumulator = new ArrayList<>();
        combinationAccumulator.add("");

        for (char currentDigit : telephoneSequence.toCharArray()) {
            List<String> intermediateResults = new ArrayList<>();
            char[] possibleLetters = telephoneDialLexicon.get(currentDigit);

            for (String existingCombination : combinationAccumulator) {
                for (char letter : possibleLetters) {
                    intermediateResults.add(existingCombination + letter);
                }
            }

            // And update the accumulator with new combinations
            combinationAccumulator = intermediateResults;
        }

        Collections.sort(combinationAccumulator);

        return combinationAccumulator;
    }

    public static void main(String[] args) {
        PhoneNumberLetterCombinations letterGenerator = new PhoneNumberLetterCombinations();
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            try {
                System.out.println("\n--- Telephone Letter Combination Composer ---");
                System.out.println("Enter telephone digits (2-9, max 4 digits) or type 'conclude':");

                String userInput = consoleReader.readLine().trim();

                if ("conclude".equalsIgnoreCase(userInput)) {
                    System.out.println("Terminating Letter Combination Composer. Goodbye!");
                    break;
                }

                // Process the combinations
                List<String> letterCombinations = letterGenerator.generateLetterCombinations(userInput);

                System.out.println("Generated Letter Combinations: " + letterCombinations);

            } catch (IllegalArgumentException | IOException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
    }
}
