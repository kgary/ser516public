import com.calculator.Calculator;

/**
 * Minimal sanity-check program.
 * Instantiates the Calculator, runs add(2, 3), and prints the result.
 * Expected output: "Result: 5"
 *
 * Used by the Jenkins pipeline's "Sanity Check" stage to verify the
 * Docker image contains a working JAR.
 */
public class SanityCheck {
    public static void main(String[] args) {
        Calculator calc = new Calculator();
        int result = calc.add(2, 3);
        System.out.println("Result: " + result);
    }
}
