package bounce;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

/**
 * The component that draws the balls.
 * @version 1.34 2012-01-26
 * @author Cay Horstmann
 */
public class BallComponent extends JPanel {
    private static final int DEFAULT_WIDTH = 450;
    private static final int DEFAULT_HEIGHT = 350;

    private List<Ball> balls = new ArrayList<>();

    /**
     * Add a ball to the panel.
     * @param b the ball to add
     */
    public void add(Ball b) {
        balls.add(b);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);  // erase background
        var g2 = (Graphics2D) g;
        for (var b : balls)
            g2.fill(b.getShape());
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

}
