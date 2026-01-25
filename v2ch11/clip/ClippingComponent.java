package clip;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

import javax.swing.JComponent;

/**
 * This component draws a set of lines clipped by the outline of a set of letters.
 */
public class ClippingComponent extends JComponent {
    private static final Dimension PREFERRED_SIZE = new Dimension(300, 300);

    @Override
    protected void paintComponent(Graphics g) {
        var g2 = (Graphics2D) g;
        FontRenderContext context = g2.getFontRenderContext();
        var f = new Font("Serif", Font.PLAIN, 72);
        var clipShape = new GeneralPath();

        var layout = new TextLayout("Hello", f, context);
        AffineTransform transform = AffineTransform.getTranslateInstance(0, 72);
        Shape outline = layout.getOutline(transform);
        clipShape.append(outline, false);

        layout = new TextLayout("World", f, context);
        transform = AffineTransform.getTranslateInstance(0, 144);
        outline = layout.getOutline(transform);
        clipShape.append(outline, false);

        g2.draw(clipShape);
        g2.clip(clipShape);

        int NLINES = 50;
        var p = new Point2D.Double(0, 0);
        for (int i = 0; i < NLINES; i++) {
            double x = (2 * getWidth() * i) / NLINES;
            double y = (2 * getHeight() * (NLINES - 1 - i)) / NLINES;
            var q = new Point2D.Double(x, y);
            g2.draw(new Line2D.Double(p, q)); // lines are clipped
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return PREFERRED_SIZE;
    }
}
