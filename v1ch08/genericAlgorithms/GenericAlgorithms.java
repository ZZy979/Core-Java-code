package genericAlgorithms;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.function.IntFunction;

/**
 * @version 1.00 2015-05-21
 * @author Cay Horstmann
 */
public class GenericAlgorithms {
    public static void main(String[] args) {
        Pair<String> p = Pair.makePair(String::new);
        System.out.println(p);

        p = Pair.makePair(String.class);
        System.out.println(p);

        String[] ss = ArrayAlg.minmax("Tom", "Dick", "Harry");
        System.out.println(Arrays.toString(ss));

        ss = ArrayAlg.minmax((IntFunction<String[]>) String[]::new, "Tom", "Dick", "Harry");
        System.out.println(Arrays.toString(ss));
    }
}

class ArrayAlg {
    @SuppressWarnings("unchecked")
    public static <T extends Comparable> T[] minmax(IntFunction<T[]> constr, T... a) {
        T[] result = constr.apply(2);
        T min = a[0];
        T max = a[0];
        for (int i = 1; i < a.length; i++) {
            if (min.compareTo(a[i]) > 0) min = a[i];
            if (max.compareTo(a[i]) < 0) max = a[i];
        }
        result[0] = min;
        result[1] = max;
        return result;
    }

    @SuppressWarnings("unchecked")
    public static <T extends Comparable> T[] minmax(T... a) {
        var result = (T[]) Array.newInstance(a.getClass().getComponentType(), 2);
        T min = a[0];
        T max = a[0];
        for (int i = 1; i < a.length; i++) {
            if (min.compareTo(a[i]) > 0) min = a[i];
            if (max.compareTo(a[i]) < 0) max = a[i];
        }
        result[0] = min;
        result[1] = max;
        return result;
    }
}
