public class ArrayAlgTest {
    public static void main(String[] args) {
        double[] a = {1.1, 2.2, 3.3, 4.4, 5.5};
        ArrayAlg.multiply(a, 0.8);
        for (double x : a)
            System.out.printf("%f ", x);
        System.out.println();
    }
}
