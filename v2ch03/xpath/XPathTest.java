package xpath;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import java.nio.file.Path;
import java.util.Scanner;

import javax.xml.catalog.CatalogFeatures;
import javax.xml.catalog.CatalogManager;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathEvaluationResult;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import javax.xml.xpath.XPathNodes;

/**
 * This program evaluates XPath expressions.
 * @version 1.1 2018-04-06
 * @author Cay Horstmann
 */
public class XPathTest {
    public static void main(String[] args) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        // Avoid a delay in parsing an XHTML file--see the first note in Section 3.3.1
        if (args.length >= 2 && args[1].equals("true")) {
            builder.setEntityResolver(CatalogManager.catalogResolver(
                    CatalogFeatures.defaults(),
                    Path.of("xpath/catalog.xml").toAbsolutePath().toUri()));
        }

        XPathFactory xpfactory = XPathFactory.newInstance();
        XPath path = xpfactory.newXPath();
        try (var in = new Scanner(System.in)) {
            String filename;
            if (args.length == 0) {
                System.out.print("Input file: ");
                filename = in.nextLine();
            }
            else
                filename = args[0];

            Document doc = builder.parse(filename);
            boolean done = false;
            while (!done) {
                System.out.print("XPath expression (empty line to exit): ");
                String expression = in.nextLine();
                if (expression.strip().isEmpty()) done = true;
                else {
                    try {
                        XPathEvaluationResult<?> result = path.evaluateExpression(expression, doc);
                        if (result.type() == XPathEvaluationResult.XPathResultType.NODESET) {
                            for (Node n : (XPathNodes) result.value())
                                System.out.println(description(n));
                        }
                        else if (result.type() == XPathEvaluationResult.XPathResultType.NODE)
                            System.out.println(description((Node) result.value()));
                        else
                            System.out.println(result.value());
                    }
                    catch (XPathExpressionException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        }
    }

    public static String description(Node n) {
        if (n instanceof Element)
            return "Element " + n.getNodeName();
        else if (n instanceof Attr)
            return "Attribute " + n;
        else
            return n.toString();
    }
}
