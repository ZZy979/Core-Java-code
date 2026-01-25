package area;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JComponent;

/**
 * This component draws an ellipse and a rectangle,
 * and combines them using different constructive area geometry operations.
 */
public class AreaComponent extends JComponent {
    public enum CombineType {
        ADD, SUBTRACT, INTERSECT, EXCLUSIVE_OR
    }

    private static final Dimension PREFERRED_SIZE = new Dimension(400, 300);
    private CombineType combineType;
    private Ellipse2D ellipse;
    private Rectangle2D rectangle;

    public AreaComponent() {
        combineType = CombineType.ADD;
        ellipse = new Ellipse2D.Double(50, 50, 160, 100);
        rectangle = new Rectangle2D.Double(110, 90, 160, 100);
    }

    @Override
    protected void paintComponent(Graphics g) {
        var g2 = (Graphics2D) g;
        var area = new Area(ellipse);
        var area2 = new Area(rectangle);
        if (combineType == CombineType.ADD)
            area.add(area2);
        else if (combineType == CombineType.SUBTRACT)
            area.subtract(area2);
        else if (combineType == CombineType.INTERSECT)
            area.intersect(area2);
        else if (combineType == CombineType.EXCLUSIVE_OR)
            area.exclusiveOr(area2);

        g2.fill(area);
        g2.draw(ellipse);
        g2.draw(rectangle);
    }

    public void setCombineType(CombineType combineType) {
        this.combineType = combineType;
        repaint();
    }

    @Override
    public Dimension getPreferredSize() {
        return PREFERRED_SIZE;
    }
}
