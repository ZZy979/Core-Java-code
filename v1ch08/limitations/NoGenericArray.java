package limitations;

import java.util.Arrays;

public class NoGenericArray {
    public static void main(String[] args) {
        String[] fl = minmax("Tom", "Dick", "Harry");
        System.out.println(Arrays.toString(fl));
    }

    public static <T extends Comparable> T[] minmax(T... a) {
        var result = new Comparable[2];
        T min = a[0];
        T max = a[0];
        for (int i = 1; i < a.length; i++) {
            if (min.compareTo(a[i]) > 0) min = a[i];
            if (max.compareTo(a[i]) < 0) max = a[i];
        }
        result[0] = min;
        result[1] = max;
        return (T[]) result; // compiles with warning
    }
}
