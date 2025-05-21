package zip;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * @version 1.42 2018-03-17
 * @author Cay Horstmann
 */
public class ZipTest {
    public static void main(String[] args) throws IOException {
        String zipname = args[0];
        showContents(zipname);
        System.out.println("---");
        showContents2(zipname);
    }

    public static void showContents(String zipname) throws IOException {
        // Here, we use the classic zip API
        try (var zin = new ZipInputStream(new FileInputStream(zipname))) {
            boolean done = false;
            while (!done) {
                ZipEntry entry = zin.getNextEntry();
                if (entry == null) {
                    done = true;
                }
                else {
                    System.out.println(entry.getName());
                    var in = new Scanner(zin, StandardCharsets.UTF_8);
                    while (in.hasNextLine())
                        System.out.println("   " + in.nextLine());
                    // DO NOT CLOSE zin
                    zin.closeEntry();
                }
            }
        }
    }

    public static void showContents2(String zipname) throws IOException {
        FileSystem fs = FileSystems.newFileSystem(Path.of(zipname));
        Files.walkFileTree(fs.getPath("/"), new SimpleFileVisitor<>() {
            @Override
            public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {
                System.out.println(path);
                for (String line : Files.readAllLines(path, StandardCharsets.UTF_8))
                    System.out.println("   " + line);
                return FileVisitResult.CONTINUE;
            }
        });
    }
}
