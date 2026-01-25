package renderQuality;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JComponent;

import static util.ResourceUtil.getImageIcon;

/**
 * This component produces a drawing that shows the effect of rendering hints.
 */
public class RenderQualityComponent extends JComponent {
    private static final Dimension PREFERRED_SIZE = new Dimension(750, 150);
    private RenderingHints hints = new RenderingHints(null);
    private Image image;

    public RenderQualityComponent() {
        image = getImageIcon("/face.gif").getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHints(hints);

        g2.draw(new Ellipse2D.Double(10, 10, 60, 50));
        g2.setFont(new Font("Serif", Font.ITALIC, 40));
        g2.drawString("Hello", 75, 50);

        g2.draw(new Rectangle2D.Double(200, 10, 40, 40));
        g2.draw(new Line2D.Double(201, 11, 239, 49));

        g2.drawImage(image, 250, 10, 100, 100, null);
    }

    /**
     * Sets the hints and repaints.
     * @param h the rendering hints
     */
    public void setRenderingHints(RenderingHints h) {
        hints = h;
        repaint();
    }

    @Override
    public Dimension getPreferredSize() {
        return PREFERRED_SIZE;
    }
}
