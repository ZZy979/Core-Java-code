package post;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;

/**
 * This program demonstrates how to use the URLConnection class for a POST request.
 * @version 1.43 2021-06-17
 * @author Cay Horstmann
 */
public class PostTest {
    public static void main(String[] args) throws IOException {
        String propsFilename = args.length > 0 ? args[0] : "post/post.properties";
        var props = new Properties();
        try (Reader in = Files.newBufferedReader(Path.of(propsFilename), StandardCharsets.UTF_8)) {
            props.load(in);
        }
        String urlString = props.remove("url").toString();
        Object userAgent = props.remove("User-Agent");
        Object redirects = props.remove("redirects");
        CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));
        String result = doPost(new URL(urlString), props,
                userAgent == null ? null : userAgent.toString(),
                redirects == null ? -1 : Integer.parseInt(redirects.toString()));
        System.out.println(result);
    }

    /**
     * Do an HTTP POST.
     * @param url the URL to post to
     * @param nameValuePairs the query parameters
     * @param userAgent the user agent to use, or null for the default user agent
     * @param redirects the number of redirects to follow manually, or -1 for automatic
     * redirects
     * @return the data returned from the server
     */
    public static String doPost(URL url, Map<Object, Object> nameValuePairs, String userAgent,
            int redirects) throws IOException {
        var connection = (HttpURLConnection) url.openConnection();
        if (userAgent != null)
            connection.setRequestProperty("User-Agent", userAgent);

        if (redirects >= 0)
            connection.setInstanceFollowRedirects(false);

        connection.setDoOutput(true);

        try (var out = new PrintWriter(connection.getOutputStream())) {
            boolean first = true;
            for (Map.Entry<Object, Object> pair : nameValuePairs.entrySet()) {
                if (first) first = false;
                else out.print('&');
                String name = pair.getKey().toString();
                String value = pair.getValue().toString();
                out.print(name);
                out.print('=');
                out.print(URLEncoder.encode(value, StandardCharsets.UTF_8));
            }
        }
        String encoding = connection.getContentEncoding();
        if (encoding == null) encoding = "UTF-8";

        if (redirects > 0) {
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_MOVED_PERM
                    || responseCode == HttpURLConnection.HTTP_MOVED_TEMP
                    || responseCode == HttpURLConnection.HTTP_SEE_OTHER) {
                String location = connection.getHeaderField("Location");
                if (location != null) {
                    URL base = connection.getURL();
                    connection.disconnect();
                    return doPost(new URL(base, location), nameValuePairs, userAgent, redirects - 1);
                }
            }
        }
        else if (redirects == 0) {
            throw new IOException("Too many redirects");
        }

        var response = new StringBuilder();
        try (var in = new Scanner(connection.getInputStream(), encoding)) {
            while (in.hasNextLine()) {
                response.append(in.nextLine());
                response.append("\n");
            }
        }
        catch (IOException e) {
            InputStream err = connection.getErrorStream();
            if (err == null) throw e;
            try (var in = new Scanner(err)) {
                response.append(in.nextLine());
                response.append("\n");
            }
        }

        return response.toString();
    }
}
