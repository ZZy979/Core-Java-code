package fill;

import javax.swing.JFrame;

/**
 * A frame that contains a component with drawings
 */
public class FillFrame extends JFrame {
    public FillFrame() {
        setTitle("FillTest");
        add(new FillComponent());
        pack();
    }
}
