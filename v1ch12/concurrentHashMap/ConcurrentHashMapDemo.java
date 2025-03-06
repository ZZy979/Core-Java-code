package concurrentHashMap;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This program demonstrates concurrent hash maps.
 * @version 1.0 2018-01-04
 * @author Cay Horstmann
 */
public class ConcurrentHashMapDemo {
    public static ConcurrentHashMap<String, Long> map = new ConcurrentHashMap<>();

    /**
     * Adds all words in the given file to the concurrent hash map.
     * @param file a file
     */
    public static void process(Path file) {
        try (var in = new Scanner(file)) {
            while (in.hasNext()) {
                String word = in.next();
                map.merge(word, 1L, Long::sum);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns all descendants of a given directory--see Chapters 1 and 2 of Volume II
     * @param rootDir the root directory
     * @return a set of all descendants of the root directory
     */
    public static Set<Path> descendants(Path rootDir) throws IOException {
        try (Stream<Path> entries = Files.walk(rootDir)) {
            return entries.collect(Collectors.toSet());
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        int processors = Runtime.getRuntime().availableProcessors();
        ExecutorService executor = Executors.newFixedThreadPool(processors);
        Path pathToRoot = Path.of(args[0]);
        for (Path p : descendants(pathToRoot)) {
            if (p.getFileName().toString().endsWith(".java"))
                executor.execute(() -> process(p));
        }
        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.MINUTES);
        map.forEach((k, v) -> {
            if (v >= 10)
                System.out.println(k + " occurs " + v + " times");
        });
    }
}
