package timeline;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @version 1.01 2021-09-06
 * @author Cay Horstmann
 */
public class TimeLine {
    public static void main(String[] args) {
        Instant start = Instant.now();
        runAlgorithm();
        Instant end = Instant.now();
        Duration timeElapsed = Duration.between(start, end);
        System.out.printf("%d milliseconds\n", timeElapsed.toMillis());

        Instant start2 = Instant.now();
        runAlgorithm2();
        Instant end2 = Instant.now();
        Duration timeElapsed2 = Duration.between(start2, end2);
        System.out.printf("%d milliseconds\n", timeElapsed2.toMillis());

        boolean overTenTimesFaster = timeElapsed.multipliedBy(10).minus(timeElapsed2).isNegative();
        System.out.printf("The first algorithm is %smore than ten times faster", overTenTimesFaster ? "" : "not ");
    }

    public static void runAlgorithm() {
        int size = 10;
        List<Integer> list = new Random().ints()
                .map(i -> i % 100)
                .limit(size)
                .boxed()
                .collect(Collectors.toCollection(ArrayList::new));
        Collections.sort(list);
        System.out.println(list);
    }

    public static void runAlgorithm2() {
        int size = 10;
        List<Integer> list = new Random().ints()
                .map(i -> i % 100)
                .limit(size)
                .boxed()
                .collect(Collectors.toCollection(ArrayList::new));
        while (!IntStream.range(1, list.size()).allMatch(i -> list.get(i - 1).compareTo(list.get(i)) <= 0))
            Collections.shuffle(list);
        System.out.println(list);
    }
}
