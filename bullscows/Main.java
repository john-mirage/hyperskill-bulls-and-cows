package bullscows;

public class Main {
    public static void main(String[] args) {
        try {
            int codeLength = UserInterface.askForCodeLength();
            int codeSymbols = UserInterface.askForCodeSymbols(codeLength);
            Code code = new Code(codeLength, codeSymbols);
            String generatedCode = code.generateCode();
            System.out.printf("The secret is prepared: %s %s.\n", "*".repeat(code.getCodeLength()), code.getSymbolRange());
            System.out.println("Okay, let's start a game!");
            int turn = 1;
            while (true) {
                System.out.println("Turn " + turn + ":");
                String guess = UserInterface.askForCodeGuess(codeLength);
                Grade grade = Code.getGrade(generatedCode, guess);
                System.out.println(grade);
                if (guess.equals(generatedCode)) {
                    break;
                }
                turn++;
            }
            System.out.println("Congratulations! You guessed the secret code.");
        } catch (CodeException | GradeException e) {
            System.out.println(e.getMessage());
        }
    }
}
