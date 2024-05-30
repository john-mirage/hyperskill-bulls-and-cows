package bullscows;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please, enter the secret code's length:");
        String lengthInput = scanner.nextLine();
        try {
            int codeLength = Integer.parseInt(lengthInput);
            if (codeLength >= 1 && codeLength <= 36) {
                System.out.println("Input the number of possible symbols in the code:");
                String symbolsInput = scanner.nextLine();
                try {
                    int codeSymbols = Integer.parseInt(symbolsInput);
                    if (codeSymbols >= 1 && codeSymbols <= 36) {
                        if (codeSymbols >= codeLength) {
                            String code = createCode(codeLength, codeSymbols);
                            System.out.println("Okay, let's start a game!");
                            int turn = 1;
                            while (true) {
                                String guess = askForGuess(scanner, turn, codeLength);
                                System.out.println(grade(guess, code));
                                if (guess.equals(code)) {
                                    break;
                                }
                                turn++;
                            }
                            System.out.println("Congratulations! You guessed the secret code.");
                        } else {
                            System.out.println("Error: it's not possible to generate a code with a length of " + codeLength + " with " + codeSymbols + " unique symbols.");
                        }
                    } else {
                        System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Error: \"" + symbolsInput + "\" isn't a valid number.");
                }
            } else {
                System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: \"" + lengthInput + "\" isn't a valid number.");
        }
    }

    public static String askForGuess(Scanner scanner, int turn, int length) {
        while (true) {
            System.out.println("Turn " + turn + ":");
            String guess = scanner.nextLine();
            if (guess.length() == length && guess.matches("^[0-9a-z]+$")) {
                return guess;
            } else {
                System.out.println("Error: Not a code");
            }
        }
    }

    public static String getSymbolRange(int codeSymbols) {
        if (codeSymbols > 10) {
            return "(0-9, a-" + (char) (codeSymbols - 10 + 96) + ")";
        } else {
            return "(0-" + (codeSymbols - 1) + ")";
        }
    }

    public static String createCode(int codeLength, int codeSymbols) {
        Random random = new Random();
        StringBuilder code = new StringBuilder();
        while (code.length() < codeLength) {
            int randomNumber = random.nextInt(codeSymbols);
            if (randomNumber < 10) {
                String number = String.valueOf(randomNumber);
                if (!code.toString().contains(number)) {
                    code.append(number);
                }
            } else {
                String letter = String.valueOf((char) (randomNumber - 10 + 97));
                if (!code.toString().contains(letter)) {
                    code.append(letter);
                }
            }
        }
        System.out.println("The secret is prepared: " + "*".repeat(codeLength) + " " + getSymbolRange(codeSymbols) + ".");
        return code.toString();
    }
    
    public static String grade(String guess, String code) {
        int cows = 0;
        int bulls = 0;
        for (int i = 0; i < code.length(); i++) {
            if (code.charAt(i) == guess.charAt(i)) {
                bulls++;
            } else if (code.contains(String.valueOf(guess.charAt(i)))) {
                cows++;
            }
        }
        return "Grade: " + bulls + " bull(s) and " + cows + " cow(s). The secret code is " + code;
    }
}
