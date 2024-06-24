package bullscows;

import java.util.Random;

public class Code {
    private final Random random;
    private final int codeLength;
    private final int codeSymbols;

    public Code(int codeLength, int codeSymbols) throws CodeException {
        if (codeLength < 1 || codeLength > 36) {
            throw new CodeException("Code length must be between 1 and 36");
        }
        if (codeSymbols < 1 || codeSymbols > 36) {
            throw new CodeException("Code symbols must be between 1 and 36");
        }
        if (codeSymbols < codeLength) {
            throw new CodeException("Error: it's not possible to generate a code with a length of %d with %d unique symbols.\n".formatted(codeLength, codeSymbols));
        }
        this.random = new Random();
        this.codeLength = codeLength;
        this.codeSymbols = codeSymbols;
    }

    public int getCodeLength() {
        return this.codeLength;
    }

    public String getSymbolRange() {
        if (this.codeSymbols > 10) {
            return "(0-9, a-" + (char) (this.codeSymbols - 10 + 96) + ")";
        } else {
            return "(0-" + (this.codeSymbols - 1) + ")";
        }
    }

    public String generateCode() {
        StringBuilder code = new StringBuilder();
        while (code.length() < codeLength) {
            int randomNumber = random.nextInt(this.codeSymbols);
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
        return code.toString();
    }

    public static Grade getGrade(String generatedCode, String guess) throws CodeException {
        if (!generatedCode.matches("^[0-9a-z]+$")) {
            throw new CodeException("Error: The generated code is not a valid code.");
        }
        if (!guess.matches("^[0-9a-z]+$")) {
            throw new CodeException("Error: The guess is not a valid code.");
        }
        int cows = 0;
        int bulls = 0;
        for (int i = 0; i < generatedCode.length(); i++) {
            if (generatedCode.charAt(i) == guess.charAt(i)) {
                bulls++;
            } else if (generatedCode.contains(String.valueOf(guess.charAt(i)))) {
                cows++;
            }
        }
        return new Grade(bulls, cows);
    }
}
