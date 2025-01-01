package mouse;

import javax.swing.JFrame;

/**
 * A frame containing a panel for testing mouse operations
 */
public class MouseFrame extends JFrame {
    public MouseFrame() {
        setTitle("MouseTest");
        add(new MouseComponent());
        pack();
    }
}
