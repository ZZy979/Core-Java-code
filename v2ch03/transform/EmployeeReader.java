package transform;

import org.xml.sax.ContentHandler;
import org.xml.sax.DTDHandler;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.AttributesImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * This class reads the flat file employee.dat and reports SAX parser events to act as if it
 * was parsing an XML file.
 */
public class EmployeeReader implements XMLReader {
    private ContentHandler handler;

    @Override
    public void parse(InputSource source) throws IOException, SAXException {
        InputStream stream = source.getByteStream();
        var in = new BufferedReader(new InputStreamReader(stream));
        var atts = new AttributesImpl();

        if (handler == null)
            throw new SAXException("No content handler");

        handler.startDocument();
        handler.startElement("", "staff", "staff", atts);
        boolean done = false;
        while (!done) {
            String line = in.readLine();
            if (line == null) done = true;
            else {
                handler.startElement("", "employee", "employee", atts);
                var t = new StringTokenizer(line, "|");

                handler.startElement("", "name", "name", atts);
                String s = t.nextToken();
                handler.characters(s.toCharArray(), 0, s.length());
                handler.endElement("", "name", "name");

                handler.startElement("", "salary", "salary", atts);
                s = t.nextToken();
                handler.characters(s.toCharArray(), 0, s.length());
                handler.endElement("", "salary", "salary");

                atts.addAttribute("", "year", "year", "CDATA", t.nextToken());
                atts.addAttribute("", "month", "month", "CDATA", t.nextToken());
                atts.addAttribute("", "day", "day", "CDATA", t.nextToken());
                handler.startElement("", "hiredate", "hiredate", atts);
                handler.endElement("", "hiredate", "hiredate");
                atts.clear();

                handler.endElement("", "employee", "employee");
            }
        }

        handler.endElement("", "staff", "staff");
        handler.endDocument();
    }

    @Override
    public void setContentHandler(ContentHandler handler) {
        this.handler = handler;
    }

    @Override
    public ContentHandler getContentHandler() {
        return handler;
    }

    // the following methods are just do-nothing implementations
    @Override public void parse(String systemId) {}
    @Override public void setErrorHandler(ErrorHandler handler) {}
    @Override public ErrorHandler getErrorHandler() { return null; }
    @Override public void setDTDHandler(DTDHandler handler) {}
    @Override public DTDHandler getDTDHandler() { return null; }
    @Override public void setEntityResolver(EntityResolver resolver) {}
    @Override public EntityResolver getEntityResolver() { return null; }
    @Override public void setProperty(String name, Object value) {}
    @Override public Object getProperty(String name) { return null; }
    @Override public void setFeature(String name, boolean value) {}
    @Override public boolean getFeature(String name) { return false; }
}
