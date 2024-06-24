package bullscows;

public class Grade {
    private final int bulls;
    private final int cows;

    public Grade(int bulls, int cows) throws GradeException {
        if (bulls < 0) {
            throw new GradeException("Bulls must be a positive integer");
        }
        if (cows < 0) {
            throw new GradeException("Cows must be a positive integer");
        }
        this.bulls = bulls;
        this.cows = cows;
    }

    @Override
    public String toString() {
        String bulls = this.bulls > 1 ? "bulls" : "bull";
        String cows = this.cows > 1 ? "cows" : "cow";
        return String.format("Grade: %d %s and %d %s", this.bulls, bulls, this.cows, cows);
    }
}
