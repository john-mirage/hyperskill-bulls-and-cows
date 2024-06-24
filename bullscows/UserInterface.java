package bullscows;

import java.util.Scanner;

public class UserInterface {
    private static final Scanner scanner = new Scanner(System.in);

    public static int askForCodeLength() throws CodeException {
        System.out.println("Please, enter the secret code's length:");
        String input = scanner.nextLine();
        try {
            int codeLength = Integer.parseInt(input);
            if (codeLength < 1 || codeLength > 36) {
                throw new CodeException("Error: The code length must be between 1 and 36");
            }
            return codeLength;
        } catch (NumberFormatException e) {
            throw new CodeException(String.format("Error: \"%s\" is not a number", input));
        }
    }

    public static int askForCodeSymbols(int codeLength) throws CodeException {
        if (codeLength < 1 || codeLength > 36) {
            throw new CodeException("Error: The code length must be between 1 and 36");
        }
        System.out.println("Input the number of possible symbols in the code:");
        String input = scanner.nextLine();
        try {
            int codeSymbols = Integer.parseInt(input);
            if (codeSymbols < 1 || codeSymbols > 36) {
                throw new CodeException("Error: maximum number of possible symbols in the code is between 1 and 36 (0-9, a-z).");
            }
            if (codeSymbols < codeLength) {
                throw new CodeException("Error: it's not possible to generate a code with a length of %d with %d unique symbols.\n".formatted(codeLength, codeSymbols));
            }
            return codeSymbols;
        } catch (NumberFormatException e) {
            throw new CodeException(String.format("Error: \"%s\" is not a number", input));
        }
    }

    public static String askForCodeGuess(int length) throws CodeException {
        if (length < 1 || length > 36) {
            throw new CodeException("Error: The code length must be between 1 and 36");
        }
        while (true) {
            String guess = scanner.nextLine();
            if (guess.length() == length && guess.matches("^[0-9a-z]+$")) {
                return guess;
            } else {
                System.out.println("Error: Not a valid guess");
            }
        }
    }
}
