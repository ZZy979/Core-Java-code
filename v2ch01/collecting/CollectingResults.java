package collecting;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @version 1.02 2019-08-28
 * @author Cay Horstmann
 */
public class CollectingResults {
    public static Stream<String> noVowels() throws IOException {
        var contents = Files.readString(Path.of("gutenberg/alice30.txt"));
        List<String> wordList = List.of(contents.split("\\PL+"));
        return wordList.stream().map(s -> s.replaceAll("[aeiouAEIOU]", ""));
    }

    public static <T> void show(String label, Set<T> set) {
        System.out.print(label + ": " + set.getClass().getName());
        System.out.println("["
                + set.stream().limit(10).map(Object::toString).collect(Collectors.joining(", "))
                + "]");
    }

    public static void main(String[] args) throws IOException {
        Iterator<Integer> iter = Stream.iterate(0, n -> n + 1).limit(10).iterator();
        while (iter.hasNext())
            System.out.println(iter.next());

        Object[] numbers = Stream.iterate(0, n -> n + 1).limit(10).toArray();
        System.out.println("Object array: " + Arrays.toString(numbers));  // Note it's an Object[] array

        try {
            var number = (Integer) numbers[0];  // OK
            System.out.println("number: " + number);
            System.out.println("The following statement throws an exception:");
            var numbers2 = (Integer[]) numbers;  // Throws exception
        }
        catch (ClassCastException e) {
            System.out.println(e);
        }

        Integer[] numbers3 = Stream.iterate(0, n -> n + 1).limit(10).toArray(Integer[]::new);
        System.out.println("Integer array: " + Arrays.toString(numbers3));  // Note it's an Integer[] array

        Set<String> noVowelSet = noVowels().collect(Collectors.toSet());
        show("noVowelSet", noVowelSet);

        TreeSet<String> noVowelTreeSet = noVowels().collect(Collectors.toCollection(TreeSet::new));
        show("noVowelTreeSet", noVowelTreeSet);

        String result = noVowels().limit(10).collect(Collectors.joining());
        System.out.println("Joining: " + result);
        result = noVowels().limit(10).collect(Collectors.joining(", "));
        System.out.println("Joining with commas: " + result);

        IntSummaryStatistics summary = noVowels().collect(Collectors.summarizingInt(String::length));
        double averageWordLength = summary.getAverage();
        double maxWordLength = summary.getMax();
        System.out.println("Average word length: " + averageWordLength);
        System.out.println("Max word length: " + maxWordLength);
        System.out.println("forEach:");
        noVowels().limit(10).forEach(System.out::println);
    }
}
