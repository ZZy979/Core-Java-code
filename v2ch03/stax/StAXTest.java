package stax;

import java.io.InputStream;
import java.net.URL;
import java.nio.file.Path;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;

/**
 * This program demonstrates how to use a StAX parser. The program prints all
 * hyperlinks of an XHTML web page. <br>
 * Usage: java stax.StAXTest URL
 * @author Cay Horstmann
 * @version 1.1 2018-05-01
 */
public class StAXTest {
    public static void main(String[] args) throws Exception {
        String urlString;
        if (args.length == 0) {
            urlString = "https://www.w3.org/";
            System.out.println("Using " + urlString);
        }
        else
            urlString = args[0];
        var url = urlString.startsWith("http") ? new URL(urlString) : Path.of(urlString).toUri().toURL();

        InputStream in = url.openStream();
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader parser = factory.createXMLStreamReader(in);
        while (parser.hasNext()) {
            int event = parser.next();
            if (event == XMLStreamConstants.START_ELEMENT) {
                if (parser.getLocalName().equals("a")) {
                    String href = parser.getAttributeValue(null, "href");
                    if (href != null)
                        System.out.println(href);
                }
            }
        }
    }
}
