package completableFutures;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

/**
 * This program reads a web page, scans it for images, loads the images and saves them locally.
 */
public class CompletableFutureDemo {
    private static final Pattern IMG_PATTERN = Pattern.compile(
            "[<]\\s*[iI][mM][gG]\\s*[^>]*[sS][rR][cC]\\s*[=]\\s*['\"]([^'\"]*)['\"][^>]*[>]");
    private ExecutorService executor = Executors.newCachedThreadPool();
    private URL urlToProcess;
    private String saveDir;

    public CompletableFuture<String> readPage(URL url) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                var contents = new String(url.openStream().readAllBytes(), StandardCharsets.UTF_8);
                System.out.println("Read page from " + url);
                return contents;
            }
            catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        }, executor);
    }

    // not time-consuming
    public List<URL> getImageURLs(String webpage) {
        try {
            var result = new ArrayList<URL>();
            Matcher matcher = IMG_PATTERN.matcher(webpage);
            while (matcher.find()) {
                var url = new URL(urlToProcess, matcher.group(1));
                result.add(url);
            }
            System.out.println("Found URLs: " + result);
            return result;
        }
        catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public CompletableFuture<List<BufferedImage>> getImages(List<URL> urls) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                var result = new ArrayList<BufferedImage>();
                for (URL url : urls) {
                    result.add(ImageIO.read(url));
                    System.out.println("Loaded " + url);
                }
                return result;
            }
            catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        }, executor);
    }

    public void saveImages(List<BufferedImage> images) {
        System.out.println("Saving " + images.size() + " images");
        try {
            for (int i = 0; i < images.size(); i++) {
                String filename = saveDir + "/image" + (i + 1) + ".png";
                ImageIO.write(images.get(i), "PNG", new File(filename));
            }
        }
        catch (IOException e) {
            throw new UncheckedIOException(e);
        }
        executor.shutdown();
    }

    public void run(URL url, String saveDir) {
        this.urlToProcess = url;
        this.saveDir = saveDir;
        CompletableFuture.completedFuture(url)
                .thenComposeAsync(this::readPage, executor)
                .thenApply(this::getImageURLs)
                .thenCompose(this::getImages)
                .thenAccept(this::saveImages);

        /*
        // or use the HTTP client:

        HttpClient client = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder(urlToProcess.toURI()).GET()
          .build();
        client.sendAsync(request, BodyProcessors.ofString())
          .thenApply(HttpResponse::body)
          .thenApply(this::getImageURLs)
          .thenCompose(this::getImages)
          .thenAccept(this::saveImages);
        */
    }

    public static void main(String[] args) throws IOException {
        new CompletableFutureDemo().run(new URL("https://horstmann.com/index.html"), args[0]);
    }
}
