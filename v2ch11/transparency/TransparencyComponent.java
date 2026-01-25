package transparency;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

/**
 * This component overlays a partially transparent rectangle on an image.
 */
public class TransparencyComponent extends JComponent {
    private static final Dimension PREFERRED_SIZE = new Dimension(500, 300);
    private BufferedImage image;

    public TransparencyComponent() {
        try {
            var url = getClass().getResource("/clouds.jpg");
            if (url != null) {
                image = ImageIO.read(url);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        var g2 = (Graphics2D) g;
        g2.drawImage(image, getWidth() / 2 - image.getWidth() / 2, getHeight() / 2 - image.getHeight() / 2, null);

        var rect = new Rectangle2D.Double(getWidth() / 2 - 100, getHeight() / 2 - 50, 200, 100);
        g2.setColor(new Color(0.7F, 0.7F, 0.0F, 0.5F));
        g2.fill(rect);
    }

    @Override
    public Dimension getPreferredSize() {
        return PREFERRED_SIZE;
    }
}
