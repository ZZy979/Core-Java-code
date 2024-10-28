package assertion;

public class MathUtils {
    public static double reciprocal(double x) {
        assert x != 0 : "Value must not be zero";
        return 1 / x;
    }
}
