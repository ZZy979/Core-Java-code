package future;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * This task counts the files in a directory and its subdirectories that contain a given keyword.
 */
public class MatchCounter implements Callable<Integer> {
    private File directory;
    private String keyword;

    /**
     * Constructs a MatchCounter.
     * @param directory the directory in which to start the search
     * @param keyword the keyword to look for
     */
    public MatchCounter(File directory, String keyword) {
        this.directory = directory;
        this.keyword = keyword;
    }

    @Override
    public Integer call() {
        int count = 0;
        try {
            File[] files = directory.listFiles();
            List<Future<Integer>> results = new ArrayList<>();

            for (File file : files) {
                if (file.isDirectory()) {
                    MatchCounter counter = new MatchCounter(file, keyword);
                    FutureTask<Integer> task = new FutureTask<>(counter);
                    results.add(task);
                    Thread t = new Thread(task);
                    t.start();
                }
                else {
                    if (search(file))
                        count++;
                }
            }

            for (Future<Integer> result : results) {
                try {
                    count += result.get();
                }
                catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }
        catch (InterruptedException e) {
        }
        return count;
    }

    /**
     * Searches a file for a given keyword.
     * @param file the file to search
     * @return true if the keyword is contained in the file
     */
    public boolean search(File file) {
        try {
            try (Scanner in = new Scanner(file, StandardCharsets.UTF_8)) {
                boolean found = false;
                while (!found && in.hasNextLine()) {
                    String line = in.nextLine();
                    if (line.contains(keyword))
                        found = true;
                }
                return found;
            }
        }
        catch (IOException e) {
            return false;
        }
    }
}
