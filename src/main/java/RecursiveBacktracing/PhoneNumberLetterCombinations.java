package RecursiveBacktracing;

import java.util.*;

public class PhoneNumberLetterCombinations {
    // Custom digit-to-letter mapping
    private final Map<Character, char[]> phoneKeypad = new HashMap<>() {{
        put('2', new char[]{'a', 'b', 'c'});
        put('3', new char[]{'d', 'e', 'f'});
        put('4', new char[]{'g', 'h', 'i'});
        put('5', new char[]{'j', 'k', 'l'});
        put('6', new char[]{'m', 'n', 'o'});
        put('7', new char[]{'p', 'q', 'r', 's'});
        put('8', new char[]{'t', 'u', 'v'});
        put('9', new char[]{'w', 'x', 'y', 'z'});
    }};

    // Custom input validator
    private void validatePhoneInput(String phoneDigits) {
        if (phoneDigits == null) {
            throw new IllegalArgumentException("Input cannot be null");
        }

        if (phoneDigits.length() > 4) {
            throw new IllegalArgumentException("Input exceeds maximum length of 4 digits");
        }

        for (char digit : phoneDigits.toCharArray()) {
            if (digit < '2' || digit > '9') {
                throw new IllegalArgumentException("Invalid digit: Only digits 2-9 are allowed");
            }
        }
    }

    // Core recursive combination generator
    public List<String> generateLetterCombinations(String phoneDigits) {
        validatePhoneInput(phoneDigits);

        // Let's first handle empty input scenario
        if (phoneDigits.isEmpty()) {
            return new ArrayList<>();
        }

        List<String> combinations = new ArrayList<>();

        // this Initiates the recursive combination generation
        generateCombinationsRecursively(phoneDigits, 0, new StringBuilder(), combinations);

        Collections.sort(combinations);

        return combinations;
    }

    // Recursive backtracking method with explicit exploration
    /**
     * Backtracking allows us to generate combinations dynamically by building solutions step-by-step,
     * essentially creating a recursive "breadth-first" exploration of all possible letter mappings.
     * So I thought this would be an elegant way to solve combinatorial problems without generating unnecessary intermediate results.
     */
    private void generateCombinationsRecursively(
            String phoneDigits,
            int currentIndex,
            StringBuilder currentCombination,
            List<String> results) {

        // Termination condition
        if (currentIndex == phoneDigits.length()) {
            results.add(currentCombination.toString());
            return;
        }

        // This gets the possible letters for current digit
        char[] possibleLetters = phoneKeypad.get(phoneDigits.charAt(currentIndex));

        for (char letter : possibleLetters) {
            // Append current letter
            currentCombination.append(letter);

            // Recursive call to next digit
            generateCombinationsRecursively(
                    phoneDigits,
                    currentIndex + 1,
                    currentCombination,
                    results
            );

            // Backtrack by removing last added letter
            currentCombination.deleteCharAt(currentCombination.length() - 1);
        }
    }

    public static void main(String[] args) {
        PhoneNumberLetterCombinations combinator = new PhoneNumberLetterCombinations();
        Scanner inputScanner = new Scanner(System.in);

        while (true) {
            try {
                System.out.println("\nPhone Letter Combination Generator");
                System.out.println("Enter phone digits (2-9, max 4 digits) or type 'quit':");

                String userInput = inputScanner.nextLine().trim();

                // Exit condition
                if ("quit".equalsIgnoreCase(userInput)) {
                    System.out.println("Exiting Combination Generator. Goodbye!");
                    break;
                }

                // Process and display combinations
                List<String> letterCombinations = combinator.generateLetterCombinations(userInput);

                System.out.print("Output: ");
                System.out.println(letterCombinations);

            } catch (IllegalArgumentException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }

        inputScanner.close();
    }
}