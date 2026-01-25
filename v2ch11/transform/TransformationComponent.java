package transform;

import java.awt.BasicStroke;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

import javax.swing.JComponent;

/**
 * This component draws a square and applies coordinate transformation.
 */
public class TransformationComponent extends JComponent {
    private static final Dimension PREFERRED_SIZE = new Dimension(600, 400);
    private Rectangle2D rect;
    private AffineTransform transform;
    private Stroke solidStroke;
    private Stroke dashedStroke;

    public TransformationComponent() {
        rect = new Rectangle2D.Double(0, 0, 100, 100);
        transform = new AffineTransform();
        solidStroke = new BasicStroke(1);
        dashedStroke = new BasicStroke(1, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER,
                10, new float[] {10, 10}, 0);
    }

    @Override
    protected void paintComponent(Graphics g) {
        var g2 = (Graphics2D) g;

        // draw original square
        g2.setStroke(dashedStroke);
        g2.draw(rect);

        // draw transformed square
        AffineTransform oldTransform = g2.getTransform();
        g2.transform(transform);
        g2.setStroke(solidStroke);
        g2.draw(rect);
        g2.setTransform(oldTransform);
    }

    public void setTransform(AffineTransform transform) {
        this.transform = transform;
        repaint();
    }

    @Override
    public Dimension getPreferredSize() {
        return PREFERRED_SIZE;
    }
}
