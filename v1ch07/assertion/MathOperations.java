package assertion;

public class MathOperations {
    public static double squareRoot(double x) {
        assert x >= 0 : "Value must be non-negative";
        return Math.sqrt(x);
    }
}
