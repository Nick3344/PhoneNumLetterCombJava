package QueueBasedApproach; 

import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class PhoneNumberLetterCombinations {
    // Customized digit-to-letter mapping
    private final Map<Character, char[]> telephoneDialMap = new HashMap<>() {{
        put('2', new char[]{'a', 'b', 'c'});
        put('3', new char[]{'d', 'e', 'f'});
        put('4', new char[]{'g', 'h', 'i'});
        put('5', new char[]{'j', 'k', 'l'});
        put('6', new char[]{'m', 'n', 'o'});
        put('7', new char[]{'p', 'q', 'r', 's'});
        put('8', new char[]{'t', 'u', 'v'});
        put('9', new char[]{'w', 'x', 'y', 'z'});
    }};

    private void validateTelephoneInput(String phoneSequence) {
        if (phoneSequence == null) {
            throw new IllegalArgumentException("Telephone sequence cannot be null");
        }

        if (phoneSequence.length() > 4) {
            throw new IllegalArgumentException("Maximum telephone sequence length is 4 digits");
        }

        for (char digit : phoneSequence.toCharArray()) {
            if (!telephoneDialMap.containsKey(digit)) {
                throw new IllegalArgumentException("Invalid digit: Only digits 2-9 are permitted");
            }
        }
    }

    // Core letter combination generation method
    public List<String> generateLetterCombinations(String telephoneSequence) {
        validateTelephoneInput(telephoneSequence);

        // Let's handle empty input scenario
        if (telephoneSequence.isEmpty()) {
            return new ArrayList<>();
        }

        // Queue-based combination generation
        Queue<String> combinationQueue = new LinkedList<>();
        combinationQueue.offer("");

        for (char currentDigit : telephoneSequence.toCharArray()) {
            int currentQueueSize = combinationQueue.size();
            char[] possibleLetters = telephoneDialMap.get(currentDigit);

            for (int iteration = 0; iteration < currentQueueSize; iteration++) {
                String currentCombination = combinationQueue.poll();

                for (char letter : possibleLetters) {
                    combinationQueue.offer(currentCombination + letter);
                }
            }
        }

        // And let's convert queue to sorted list
        List<String> resultCombinations = new ArrayList<>(combinationQueue);
        Collections.sort(resultCombinations);

        return resultCombinations;
    }

    public static void main(String[] args) {
        PhoneNumberLetterCombinations letterGenerator = new PhoneNumberLetterCombinations();
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            try {
                System.out.println("\n--- Telephone Letter Combination Generator ---");
                System.out.println("Enter telephone digits (2-9, max 4 digits) or type 'terminate':");

                String userInput = consoleReader.readLine().trim();

                // Exit condition
                if ("terminate".equalsIgnoreCase(userInput)) {
                    System.out.println("Terminating Letter Combination Generator. Farewell!");
                    break;
                }

                // Process and display combinations
                List<String> letterCombinations = letterGenerator.generateLetterCombinations(userInput);

                System.out.println("Generated Combinations: " + letterCombinations);

            } catch (IllegalArgumentException | IOException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
    }
}


