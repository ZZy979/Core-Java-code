package sax;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.io.InputStream;
import java.net.URL;
import java.nio.file.Path;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * This program demonstrates how to use a SAX parser. The program prints all
 * hyperlinks of an XHTML web page. <br>
 * Usage: java sax.SAXTest URL
 * @version 1.01 2018-05-01
 * @author Cay Horstmann
 */
public class SAXTest {
    public static void main(String[] args) throws Exception {
        String urlString;
        if (args.length == 0) {
            urlString = "https://www.w3.org/";
            System.out.println("Using " + urlString);
        }
        else
            urlString = args[0];
        var url = urlString.startsWith("http") ? new URL(urlString) : Path.of(urlString).toUri().toURL();

        var handler = new DefaultHandler() {
            @Override
            public void startElement(String namespaceURI, String lname, String qname, Attributes attrs) {
                if (lname.equals("a") && attrs != null) {
                    for (int i = 0; i < attrs.getLength(); i++) {
                        String aname = attrs.getLocalName(i);
                        if (aname.equals("href"))
                            System.out.println(attrs.getValue(i));
                    }
                }
            }
        };

        SAXParserFactory factory = SAXParserFactory.newInstance();
        factory.setNamespaceAware(true);
        factory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
        SAXParser saxParser = factory.newSAXParser();
        InputStream in = url.openStream();
        saxParser.parse(in, handler);
    }
}
