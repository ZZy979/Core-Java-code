package retire;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JComponent;

/**
 * This component draws a graph of the investment result.
 */
public class RetireComponent extends JComponent {
    private static final int PANEL_WIDTH = 400;
    private static final int PANEL_HEIGHT = 200;
    private static final Dimension PREFERRED_SIZE = new Dimension(800, 600);
    private RetireInfo info = null;
    private Color colorPre;
    private Color colorGain;
    private Color colorLoss;

    public RetireComponent() {
        setSize(PANEL_WIDTH, PANEL_HEIGHT);
    }

    /**
     * Sets the retirement information to be plotted.
     * @param newInfo the new retirement info.
     */
    public void setInfo(RetireInfo newInfo) {
        info = newInfo;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        var g2 = (Graphics2D) g;
        if (info == null) return;

        double minValue = 0;
        double maxValue = 0;
        int i;
        for (i = info.getCurrentAge(); i <= info.getDeathAge(); i++) {
            double v = info.getBalance(i);
            if (minValue > v) minValue = v;
            if (maxValue < v) maxValue = v;
        }
        if (maxValue == minValue) return;

        int barWidth = getWidth() / (info.getDeathAge() - info.getCurrentAge() + 1);
        double scale = getHeight() / (maxValue - minValue);

        for (i = info.getCurrentAge(); i <= info.getDeathAge(); i++) {
            int x1 = (i - info.getCurrentAge()) * barWidth + 1;
            int y1;
            double v = info.getBalance(i);
            int height;
            int yOrigin = (int) (maxValue * scale);

            if (v >= 0) {
                y1 = (int) ((maxValue - v) * scale);
                height = yOrigin - y1;
            }
            else {
                y1 = yOrigin;
                height = (int) (-v * scale);
            }

            if (i < info.getRetireAge())
                g2.setPaint(colorPre);
            else if (v >= 0)
                g2.setPaint(colorGain);
            else
                g2.setPaint(colorLoss);

            var bar = new Rectangle2D.Double(x1, y1, barWidth - 2, height);
            g2.fill(bar);
            g2.setPaint(Color.BLACK);
            g2.draw(bar);
        }
    }

    /**
     * Sets the color to be used before retirement.
     * @param color the desired color
     */
    public void setColorPre(Color color) {
        colorPre = color;
        repaint();
    }

    /**
     * Sets the color to be used after retirement while the account balance is positive.
     * @param color the desired color
     */
    public void setColorGain(Color color) {
        colorGain = color;
        repaint();
    }

    /**
     * Sets the color to be used after retirement when the account balance is negative.
     * @param color the desired color
     */
    public void setColorLoss(Color color) {
        colorLoss = color;
        repaint();
    }

    @Override
    public Dimension getPreferredSize() {
        return PREFERRED_SIZE;
    }
}
