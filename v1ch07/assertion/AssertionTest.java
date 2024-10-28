package assertion;

import java.util.Scanner;

public class AssertionTest {
    public static void main(String[] args) {
        try (var in = new Scanner(System.in)) {
            System.out.print("Enter a floating-point number: ");
            double x = in.nextDouble();
            double squareRoot = MathOperations.squareRoot(x);
            System.out.println("The square root is " + squareRoot);
            double reciprocal = MathUtils.reciprocal(x);
            System.out.println("The reciprocal is " + reciprocal);
        }
        catch (AssertionError e) {
            System.out.println(e);
        }
    }
}
