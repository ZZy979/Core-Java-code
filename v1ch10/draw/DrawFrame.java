package draw;

import javax.swing.JFrame;

/**
 * A frame that contains a panel with drawings.
 */
public class DrawFrame extends JFrame {
    public DrawFrame() {
        setTitle("DrawTest");
        add(new DrawComponent());
        pack();
    }
}
