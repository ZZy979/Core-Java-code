package transform;

import org.xml.sax.InputSource;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 * This program demonstrates XSL transformations. It applies a transformation to a set of
 * employee records. The records are stored in the file employee.dat and turned into XML
 * format. Specify the stylesheet on the command line, e.g.<br>
 *    java transform.TransformTest transform/makeprop.xsl
 * @version 1.05 2021-09-21
 * @author Cay Horstmann
 */
public class TransformTest {
    public static void main(String[] args) throws Exception {
        Path path;
        if (args.length > 0) path = Path.of(args[0]);
        else path = Path.of("transform", "makehtml.xsl");
        try (InputStream styleIn = Files.newInputStream(path)) {
            var styleSource = new StreamSource(styleIn);

            Transformer t = TransformerFactory.newInstance().newTransformer(styleSource);
            t.setOutputProperty(OutputKeys.INDENT, "yes");
            t.setOutputProperty(OutputKeys.METHOD, "xml");
            t.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

            try (InputStream docIn = Files.newInputStream(Path.of("transform", "employee.dat"))) {
                t.transform(new SAXSource(new EmployeeReader(), new InputSource(docIn)), new StreamResult(System.out));
            }
        }
    }
}
