package paint;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.TexturePaint;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

/**
 * This component draws an ellipse and fill it with different paint styles.
 */
public class PaintComponent extends JComponent {
    public enum PaintStyle {
        SOLID, GRADIENT, TEXTURE
    }

    private static final Dimension PREFERRED_SIZE = new Dimension(400, 300);
    private PaintStyle paintStyle;
    private Ellipse2D ellipse;
    private BufferedImage image;
    private Rectangle2D anchor;

    public PaintComponent() {
        paintStyle = PaintStyle.SOLID;
        ellipse = new Ellipse2D.Double(100, 50, 200, 150);
        try {
            var url = PaintComponent.class.getResource("/blue-ball.gif");
            if (url != null) {
                image = ImageIO.read(url);
                anchor = new Rectangle2D.Double(ellipse.getX(), ellipse.getY(), image.getWidth(), image.getHeight());
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        var g2 = (Graphics2D) g;
        if (paintStyle == PaintStyle.SOLID)
            g2.setPaint(Color.RED);
        else if (paintStyle == PaintStyle.GRADIENT)
            g2.setPaint(new GradientPaint((float) ellipse.getX(), (float) ellipse.getY(), Color.RED,
                    (float) ellipse.getMaxX(), (float) ellipse.getMaxY(), Color.YELLOW));
        else if (paintStyle == PaintStyle.TEXTURE)
            g2.setPaint(new TexturePaint(image, anchor));
        g2.fill(ellipse);
    }

    public void setPaintStyle(PaintStyle paintStyle) {
        this.paintStyle = paintStyle;
        repaint();
    }

    @Override
    public Dimension getPreferredSize() {
        return PREFERRED_SIZE;
    }
}
