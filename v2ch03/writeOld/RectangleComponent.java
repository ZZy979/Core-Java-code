package writeOld;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JComponent;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 * A component that shows a set of colored rectangles
 */
public class RectangleComponent extends JComponent {
    private static final Dimension PREFERRED_SIZE = new Dimension(300, 200);

    private List<Rectangle2D> rects;
    private List<Color> colors;
    private static Random generator = new Random();
    private DocumentBuilder builder;

    public RectangleComponent() {
        rects = new ArrayList<>();
        colors = new ArrayList<>();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        try {
            builder = factory.newDocumentBuilder();
        }
        catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    /**
     * Create a new random drawing.
     */
    public void newDrawing() {
        int n = 10 + generator.nextInt(20);
        rects.clear();
        colors.clear();
        for (int i = 1; i <= n; i++) {
            int x = generator.nextInt(getWidth());
            int y = generator.nextInt(getHeight());
            int width = generator.nextInt(getWidth() - x);
            int height = generator.nextInt(getHeight() - y);
            rects.add(new Rectangle(x, y, width, height));
            int r = generator.nextInt(256);
            int g = generator.nextInt(256);
            int b = generator.nextInt(256);
            colors.add(new Color(r, g, b));
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (rects.isEmpty()) newDrawing();
        Graphics2D g2 = (Graphics2D) g;

        // draw all rectangles
        for (int i = 0; i < rects.size(); i++) {
            g2.setPaint(colors.get(i));
            g2.fill(rects.get(i));
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return PREFERRED_SIZE;
    }

    /**
     * Creates an SVG document of the current drawing.
     * @return the DOM tree of the SVG document
     */
    public Document buildDocument() {
        String namespace = "http://www.w3.org/2000/svg";
        Document doc = builder.newDocument();
        Element svgElement = doc.createElementNS(namespace, "svg");
        doc.appendChild(svgElement);
        svgElement.setAttribute("width", Integer.toString(getWidth()));
        svgElement.setAttribute("height", Integer.toString(getHeight()));
        for (int i = 0; i < rects.size(); i++) {
            Color c = colors.get(i);
            Rectangle2D r = rects.get(i);
            Element rectElement = doc.createElementNS(namespace, "rect");
            rectElement.setAttribute("x", Double.toString(r.getX()));
            rectElement.setAttribute("y", Double.toString(r.getY()));
            rectElement.setAttribute("width", Double.toString(r.getWidth()));
            rectElement.setAttribute("height", Double.toString(r.getHeight()));
            rectElement.setAttribute("fill", "#%06x".formatted(c.getRGB() & 0xFFFFFF));
            svgElement.appendChild(rectElement);
        }
        return doc;
    }

    /**
     * Writes an SVG document of the current drawing.
     * @param writer the document destination
     */
    public void writeDocument(XMLStreamWriter writer) throws XMLStreamException {
        writer.writeStartDocument();
        writer.writeDTD("""
<!DOCTYPE svg PUBLIC "-//W3C//DTD SVG 20000802//EN" 
  "http://www.w3.org/TR/2000/CR-SVG-20000802/DTD/svg-20000802.dtd">
""");
        writer.writeStartElement("svg");
        writer.writeDefaultNamespace("http://www.w3.org/2000/svg");
        writer.writeAttribute("width", Integer.toString(getWidth()));
        writer.writeAttribute("height", Integer.toString(getHeight()));
        for (int i = 0; i < rects.size(); i++) {
            Color c = colors.get(i);
            Rectangle2D r = rects.get(i);
            writer.writeEmptyElement("rect");
            writer.writeAttribute("x", Double.toString(r.getX()));
            writer.writeAttribute("y", Double.toString(r.getY()));
            writer.writeAttribute("width", Double.toString(r.getWidth()));
            writer.writeAttribute("height", Double.toString(r.getHeight()));
            writer.writeAttribute("fill", "#%06x".formatted(c.getRGB() & 0xFFFFFF));
        }
        writer.writeEndDocument(); // closes svg element
        writer.close();
    }
}
