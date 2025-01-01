package fill;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JComponent;

/**
 * A component that displays filled rectangles and ellipses
 */
public class FillComponent extends JComponent {
    private static final int DEFAULT_WIDTH = 400;
    private static final int DEFAULT_HEIGHT = 400;

    @Override
    public void paintComponent(Graphics g) {
        var g2 = (Graphics2D) g;

        // draw a rectangle
        double leftX = 100;
        double topY = 100;
        double width = 200;
        double height = 150;

        var rect = new Rectangle2D.Double(leftX, topY, width, height);
        g2.setPaint(Color.BLACK);
        g2.draw(rect);
        g2.setPaint(Color.RED);
        g2.fill(rect); // Note that the right and bottom edge are not painted over

        // draw the enclosed ellipse
        var ellipse = new Ellipse2D.Double();
        ellipse.setFrame(rect);
        g2.setPaint(new Color(0, 128, 128)); // a dull blue-green
        g2.fill(ellipse);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }
}
